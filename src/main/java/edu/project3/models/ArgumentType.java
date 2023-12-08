package edu.project3.models;

public enum ArgumentType {
    PATH("--path"),
    FROM("--from"),
    TO("--to"),
    FORMAT("--format");

    private final String key;

    ArgumentType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static ArgumentType getTypeByValue(String value) {
        for (ArgumentType argumentType : values()) {
            if (argumentType.getKey().equals(value)) {
                return argumentType;
            }
        }
        throw new IllegalArgumentException("Incorrect command key type: " + value);
    }
}
