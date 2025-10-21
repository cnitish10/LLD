package org.example.Logger.model;

import org.example.Logger.enums.LogLevel;

public class WarnLogger extends Logger{
    public WarnLogger() {
        this.level = LogLevel.WARN;
    }

    @Override
    protected void write(String message) {
        System.out.println("Warn Logger : " + message);
    }
}
