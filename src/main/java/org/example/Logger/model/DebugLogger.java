package org.example.Logger.model;

import org.example.Logger.enums.LogLevel;

public class DebugLogger extends Logger{
    public DebugLogger() {
        this.level = LogLevel.DEBUG;
    }

    @Override
    protected void write(String message) {
        System.out.println("Debugger Logger : " + message);
    }
}
