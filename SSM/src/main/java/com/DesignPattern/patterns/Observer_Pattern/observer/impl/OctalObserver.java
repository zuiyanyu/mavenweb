package com.DesignPattern.patterns.Observer_Pattern.observer.impl;

import com.DesignPattern.patterns.Observer_Pattern.Subject;
import com.DesignPattern.patterns.Observer_Pattern.observer.Observer;

public class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}