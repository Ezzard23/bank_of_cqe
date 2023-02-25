package com.example.demo.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SysLogger {
    
    public static Logger accountlogger (){
        Logger logger = Logger.getLogger(SysLogger.class.getName());
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("C:/Users/cqezz/OneDrive/Desktop/bankAppFrontend/demo/src/SysLogger.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }        
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        //adding custom handler
        logger.addHandler(new SysHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("C:/Users/cqezz/OneDrive/Desktop/bankAppFrontend/demo/src/demo.txt", 2000, 1);
            fileHandler.setFormatter(new LogFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new LogFilter());
            logger.addHandler(fileHandler);
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

}