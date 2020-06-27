package com.mavenweb.service;

import com.mavenweb.domain.DqReportItemLocal;

import java.util.List;

public interface DqReportItemLocalService {
    void updateItemId(DqReportItemLocal dqReportItemLocal);

    String getMaxID();

    List<DqReportItemLocal> getWillBeUpdatedItems();
}
