package com.example.demo.logger;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormatter extends Formatter {

    @Override
    public String format(LogRecord rec) {
        return rec.getLongThreadID()+"::"+rec.getSourceClassName()+"::"
                +rec.getSourceMethodName()+"::"
                +new Date(rec.getMillis())+"::"
                +rec.getMessage()+"\n";
    }

}
