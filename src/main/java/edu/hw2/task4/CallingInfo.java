package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo callingInfo() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return new CallingInfo(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
    }
}
