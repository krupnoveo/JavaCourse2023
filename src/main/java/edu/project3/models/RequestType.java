package edu.project3.models;

public enum RequestType {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    PUT("PUT"),
    DELETE("DELETE"),
    CONNECT("CONNECT"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    PATCH("PATCH");

    private final String command;

    RequestType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static RequestType getRequestTypeByCommand(String command) {
        for (RequestType type : values()) {
            if (type.getCommand().equals(command)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Incorrect HTTP command: " + command);
    }
}
