package org.example.Logger.model;

import org.example.Logger.enums.LogLevel;

public class InfoLogger extends Logger{
    public InfoLogger(){
        this.level = LogLevel.INFO;
    }
    @Override
    protected void write(String message) {
        System.out.println("Info Logger " + message);
    }
}
