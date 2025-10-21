package org.example.Logger.model;

import org.example.Logger.enums.LogLevel;

public class ErrorLogger extends Logger{
    public ErrorLogger() {
        this.level = LogLevel.ERROR;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Logger : "+ message);
    }
}
