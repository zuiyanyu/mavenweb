package com.DesignPattern.patterns.Chain_of_Responsibility_Pattern.logs.Impl;

import com.DesignPattern.patterns.Chain_of_Responsibility_Pattern.logs.AbstractLogger;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}