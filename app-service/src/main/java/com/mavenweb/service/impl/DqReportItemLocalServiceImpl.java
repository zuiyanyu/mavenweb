package com.mavenweb.service.impl;

import com.mavenweb.domain.DqReportItemLocal;
import com.mavenweb.mapper.DqReportItemLocalMapper;
import com.mavenweb.service.DqReportItemLocalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DqReportItemLocalServiceImpl implements DqReportItemLocalService {
    @Resource
    DqReportItemLocalMapper dqReportItemLocalMapper ;
    @Override
    public void updateItemId(DqReportItemLocal dqReportItemLocal) {
        dqReportItemLocalMapper.updateItemId(dqReportItemLocal);
    }

    @Override
    public String getMaxID() {
        return dqReportItemLocalMapper.getMaxID();
    }

    @Override
    public List<DqReportItemLocal> getWillBeUpdatedItems() {
        return dqReportItemLocalMapper.getWillBeUpdatedItems();
    }
}
