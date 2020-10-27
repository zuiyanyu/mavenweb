package com.DesignPattern.patterns.Observer_Pattern.observer.impl;

import com.DesignPattern.patterns.Observer_Pattern.Subject;
import com.DesignPattern.patterns.Observer_Pattern.observer.Observer;

public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
