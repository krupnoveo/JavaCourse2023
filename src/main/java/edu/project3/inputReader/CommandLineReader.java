package edu.project3.inputReader;

import edu.project3.models.ArgumentType;
import java.util.HashMap;
import java.util.Map;

public class CommandLineReader implements Reader {
    private int indexOfArgument = 1;

    @Override
    public Map<ArgumentType, String> getArguments(String[] args) {
        if (args.length < 2 || !args[0].equals(ArgumentType.PATH.getKey())) {
            throw new IllegalArgumentException(
                "Incorrect input! There must be --path at start with the path to file given after"
            );
        }
        Map<ArgumentType, String> arguments = new HashMap<>();
        arguments.put(ArgumentType.FROM, null);
        arguments.put(ArgumentType.TO, null);
        arguments.put(ArgumentType.FORMAT, null);
        arguments.put(ArgumentType.PATH, getPaths(args));
        if (args.length > indexOfArgument) {
            while (indexOfArgument < args.length) {
                ArgumentType type = ArgumentType.getTypeByValue(args[indexOfArgument]);
                switch (type) {
                    case FROM, TO, FORMAT -> arguments.put(type, getArgument(args));
                    default -> { }
                }
                indexOfArgument += 1;
            }
        }
        return arguments;
    }

    private String getPaths(String[] args) {
        StringBuilder paths = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (isArgument(args[1])) {
                throw new IllegalArgumentException(
                    "No argument(-s) for key --path"
                );
            }
            if (!isArgument(args[i])) {
                paths.append(args[i]).append(" ");
                ++indexOfArgument;
            } else {
                break;
            }
        }
        return paths.toString().trim();
    }

    private String getArgument(String[] args) {
        if (indexOfArgument + 1 >= args.length) {
            throw new IllegalArgumentException(
                "No argument(-s) for key " + args[indexOfArgument]
            );
        }
        if (!isArgument(args[indexOfArgument + 1])) {
            return args[++indexOfArgument];
        }
        throw new IllegalArgumentException(
            "Expected argument for " + args[indexOfArgument] + ", but got key instead"
        );
    }

    private boolean isArgument(String arg) {
        return switch (arg) {
            case "--from", "--to", "--format" -> true;
            default -> false;
        };
    }
}
