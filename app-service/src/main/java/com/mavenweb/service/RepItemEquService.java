package com.mavenweb.service;

import com.mavenweb.domain.RepItemEqu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RepItemEquService {
    List<RepItemEqu> getRepItemEquItems();
    List<Map<String,String>> getItemIdMap() ;
    void updateRepItemEqu(List<RepItemEqu> repItemEquList);
    void updateOneRepItemEqu(@Param("repItemEqu") RepItemEqu repItemEqu);
}
