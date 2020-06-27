package com.mavenweb.mapper;

import com.mavenweb.domain.DqReportItemLocal;

import java.util.List;

public interface DqReportItemLocalMapper {

    void updateItemId(DqReportItemLocal dqReportItemLocal);
    String getMaxID() ;

    List<DqReportItemLocal> getWillBeUpdatedItems();
}
