package com.hsf_edas.service.impl;

import com.hsf_edas.service.ICalService;
import org.springframework.stereotype.Service;

@Service("calService")
public class CalServiceImp implements ICalService {
    @Override
    public String addNum(int a, int b) {
        return "hello";
    }
}
