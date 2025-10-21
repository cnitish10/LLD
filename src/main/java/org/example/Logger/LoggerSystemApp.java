package org.example.Logger;

import org.example.Logger.enums.LogLevel;
import org.example.Logger.model.Logger;
import org.example.Logger.service.LoggerService;

public class LoggerSystemApp {
    public static void main(String args[]){
        LoggerService loggerService = new LoggerService();
        Logger logger = LoggerService.infoLogger;

        logger.logMessage(LogLevel.INFO, "This is an information message.");
        logger.logMessage(LogLevel.WARN, "This is a warning.");
        logger.logMessage(LogLevel.ERROR, "This is an error message.");
        logger.logMessage(LogLevel.DEBUG, "This is a debug message.");
    }
}
