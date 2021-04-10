package com.hsf_edas.service.impl;

import com.hsf_edas.service.SayHelloService;

public class SayHelloServiceImpl implements SayHelloService {
    public String sayHello(String user) {
        return "Hello "+user+" ，Time is "+System.currentTimeMillis()+"(ms)";
    }
}