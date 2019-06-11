package com.testres.testrest.controller;

/**
 * @author konst
 */
public class Cause {
    private final String className;
    private final String message;

    public Cause(String className, String message) {
        this.className = className;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Cause{" +
                "className='" + className + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }
}
