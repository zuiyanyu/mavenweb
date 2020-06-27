package com.mavenweb.service.impl;

import com.mavenweb.mapper.DqReportItemMapper;
import com.mavenweb.service.DqReportItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DqReportItemServiceImpl implements DqReportItemService {
    @Resource
    DqReportItemMapper dqReportItemMapper ;
    @Override
    public String getMaxID() {
        return dqReportItemMapper.getMaxID();
    }
}
