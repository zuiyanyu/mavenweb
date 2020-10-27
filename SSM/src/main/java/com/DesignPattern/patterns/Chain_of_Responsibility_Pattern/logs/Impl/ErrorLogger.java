package com.DesignPattern.patterns.Chain_of_Responsibility_Pattern.logs.Impl;

import com.DesignPattern.patterns.Chain_of_Responsibility_Pattern.logs.AbstractLogger;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}