package edu.project3.startOfProgram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalizatorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Тест LogAnalizator.run()")
    public void run_shouldReturnCorrectResult() {
        String expected = """
==== Общая информация
|=================================
|              Метрика|   Значение
|             Файл(-ы)|'logs1.txt'
|       Начальная дата| 14.11.2023
|        Конечная дата| 15.11.2023
|  Количество запросов|        704
|Средний размер ответа|  1639.125b

==== Запрашиваемые адреса
|=========================
|         Адрес|Количество
|  50.43.127.92|         1
| 67.89.207.165|         1
|60.165.104.214|         1
|112.100.131.61|         1
| 89.50.187.202|         1

==== Запрашиваемые ресурсы
|=================================
|                Ресурс|Количество
|      '/time-frame.js'|         3
|  '/clear-thinking.js'|         3
|      '/structure.htm'|         3
|     '/responsive.css'|         2
|'/functionalities.htm'|         2

==== Коды ответа
|====================================
|Код|                  Имя|Количество
|200|                   OK|       602
|302|                Found|        27
|301|    Moved Permanently|        23
|500|Internal Server Error|        21
|400|          Bad Request|        18

==== Источники запроса
|==========================================================================================================================================
|                                                                                                                       Источник|Количество
|                         Mozilla/5.0 (Windows NT 4.0) AppleWebKit/5310 (KHTML, like Gecko) Chrome/39.0.846.0 Mobile Safari/5310|         2
|                                           Opera/10.90 (Macintosh; U; PPC Mac OS X 10_5_0; en-US) Presto/2.13.277 Version/13.00|         1
|                                             Opera/8.54 (Macintosh; Intel Mac OS X 10_5_3; en-US) Presto/2.11.197 Version/10.00|         1
|                         Mozilla/5.0 (Windows NT 5.2) AppleWebKit/5360 (KHTML, like Gecko) Chrome/38.0.853.0 Mobile Safari/5360|         1
|Mozilla/5.0 (Macintosh; PPC Mac OS X 10_6_5 rv:6.0; en-US) AppleWebKit/534.14.3 (KHTML, like Gecko) Version/6.1 Safari/534.14.3|         1""";
        new LogAnalizator().run(new String[] {"--path", "logs1.txt", "--from", "2023-11-14", "--to", "2023-11-15", "--format", "adoc"});
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
