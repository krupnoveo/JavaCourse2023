package edu.project3.startOfProgram;

import edu.project3.filter.DateLogFilter;
import edu.project3.filter.LogFilter;
import edu.project3.inputReader.CommandLineReader;
import edu.project3.inputReader.Reader;
import edu.project3.logsReader.LocalLogsReader;
import edu.project3.logsReader.UrlLogsReader;
import edu.project3.models.ArgumentType;
import edu.project3.models.FormatType;
import edu.project3.models.Log;
import edu.project3.models.WrapperOfLogs;
import edu.project3.output.ConsoleOutputer;
import edu.project3.output.Outputer;
import edu.project3.outputFormat.AsciiOutputFormatter;
import edu.project3.outputFormat.MarkdownOutputFormatter;
import edu.project3.outputFormat.OutputFormatter;
import edu.project3.parser.LogParser;
import edu.project3.statistics.GeneralDataStatistics;
import edu.project3.statistics.LogStatistics;
import edu.project3.statistics.RemoteAddressesStatistics;
import edu.project3.statistics.RequestedResourcesStatistics;
import edu.project3.statistics.ResponseCodesStatistics;
import edu.project3.statistics.UserAgentStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogAnalizator {
    private Map<ArgumentType, String> inputData;
    private List<String> stringLogs;
    private final Reader reader;
    private final Outputer outputer;
    private LogFilter filter;
    private OutputFormatter formatter = new MarkdownOutputFormatter();
    private WrapperOfLogs wrappedLogs;
    private List<LogStatistics> statistics;

    public LogAnalizator() {
        this.reader = new CommandLineReader();
        this.outputer = new ConsoleOutputer();
    }

    public void run(String[] args) {
        readAndSetAllInputData(args);
        getRawLogsInput();
        setFilter();
        getParsedAndFilteredLogs(inputData.get(ArgumentType.PATH).split(" "));
        setStatistics();
        print();
    }

    private void readAndSetAllInputData(String[] args) {
        inputData = reader.getArguments(args);
        FormatType format = FormatType.getFormatByValue(inputData.get(ArgumentType.FORMAT));
        if (format.equals(FormatType.ADOC)) {
            formatter = new AsciiOutputFormatter();
        }
    }

    private void getRawLogsInput() {
        String[] paths = inputData
            .get(ArgumentType.PATH)
            .split(" ");
        stringLogs = new ArrayList<>();
        for (String path : paths) {
            if (path.contains("http")) {
                stringLogs.addAll(new UrlLogsReader().readLogs(path));
            } else {
                stringLogs.addAll(new LocalLogsReader().readLogs(path));
            }
        }
    }

    private void setFilter() {
        filter = new DateLogFilter(
            inputData.get(ArgumentType.FROM),
            inputData.get(ArgumentType.TO)
        );
    }

    private void getParsedAndFilteredLogs(String[] paths) {
        List<Log> parsedLogs = stringLogs.stream()
            .map(LogParser::parse)
            .filter(log -> filter.isMatchingTheFilter(log))
            .toList();
        wrappedLogs = new WrapperOfLogs(
            List.of(paths),
            inputData.get(ArgumentType.FROM),
            inputData.get(ArgumentType.TO),
            parsedLogs
        );
    }

    private void setStatistics() {
        statistics = new ArrayList<>();
        statistics.add(new GeneralDataStatistics());
        statistics.add(new RemoteAddressesStatistics());
        statistics.add(new RequestedResourcesStatistics());
        statistics.add(new ResponseCodesStatistics());
        statistics.add(new UserAgentStatistics());
    }

    private void print() {
        for (LogStatistics statistic : statistics) {
            outputer.print(formatter.getFormattedOutput(statistic.getData(wrappedLogs)));
        }
    }
}
