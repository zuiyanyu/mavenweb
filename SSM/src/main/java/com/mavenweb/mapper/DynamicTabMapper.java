package com.mavenweb.mapper;

import com.mavenweb.domain.DynamicTab;
import org.apache.ibatis.annotations.Param;

public interface DynamicTabMapper {
    int deleteAllTabData(@Param("tabName") String tabName);

    /**
     * 动态为某张表插入数据
     * @param dynamicTab
     * @return
     */
    int dymicAddData(@Param("dynamicTab") DynamicTab dynamicTab);
}
