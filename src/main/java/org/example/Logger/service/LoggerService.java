package org.example.Logger.service;

import org.example.Logger.model.*;

public class LoggerService {
    public static Logger infoLogger;
    public static Logger warnLogger;
    public static Logger debugLogger;
    public static Logger errorLogger;

    public LoggerService(){
        this.infoLogger = new InfoLogger();
        this.debugLogger = new DebugLogger();
        this.errorLogger = new ErrorLogger();
        this.warnLogger = new WarnLogger();

        infoLogger.setNextLogger(warnLogger);
        warnLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
    }
}
