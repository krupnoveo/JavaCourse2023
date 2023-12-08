package edu.project3.models;

public enum FormatType {
    MARKDOWN("markdown"),
    ADOC("adoc");

    private final String type;

    FormatType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static FormatType getFormatByValue(String value) {
        if (value != null) {
            for (FormatType formatType : values()) {
                if (value.equals(formatType.getType())) {
                    return formatType;
                }
            }
            throw new IllegalArgumentException("Incorrect format type: " + value);
        }
        return MARKDOWN;
    }
}
