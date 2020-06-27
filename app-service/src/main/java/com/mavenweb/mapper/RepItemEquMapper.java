package com.mavenweb.mapper;

import com.mavenweb.domain.RepItemEqu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepItemEquMapper {
    List<RepItemEqu> getRepItemEquItems();
    List<Map<String,String>>  getItemIdMap() ;
    void updateRepItemEqu(@Param("list") List<RepItemEqu> repItemEquList);
    void updateOneRepItemEqu(@Param("repItemEqu") RepItemEqu repItemEqu);
}
