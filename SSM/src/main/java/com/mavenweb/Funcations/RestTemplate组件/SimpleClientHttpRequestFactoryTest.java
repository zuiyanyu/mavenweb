package com.mavenweb.Funcations.RestTemplate组件;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

public class SimpleClientHttpRequestFactoryTest {
    public static void main(String[] args) {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(2000);
    }
}
