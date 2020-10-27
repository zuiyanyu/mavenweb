package com.DesignPattern.patterns.Observer_Pattern.observer.impl;

import com.DesignPattern.patterns.Observer_Pattern.Subject;
import com.DesignPattern.patterns.Observer_Pattern.observer.Observer;

public class HexaObserver extends Observer {

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}