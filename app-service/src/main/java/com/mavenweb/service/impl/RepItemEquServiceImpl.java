package com.mavenweb.service.impl;

import com.mavenweb.domain.RepItemEqu;
import com.mavenweb.mapper.RepItemEquMapper;
import com.mavenweb.service.RepItemEquService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RepItemEquServiceImpl implements RepItemEquService {
    @Resource
    RepItemEquMapper repItemEquMapper ;
    @Override
    public List<RepItemEqu> getRepItemEquItems() {
        //  List<RepItemEqu> getrepItemEquItems();
        return repItemEquMapper.getRepItemEquItems();
    }

    @Override
    public  List<Map<String,String>> getItemIdMap() {
        return repItemEquMapper.getItemIdMap();
    }

    @Override
    public void updateRepItemEqu(List<RepItemEqu> repItemEquList) {
        repItemEquMapper.updateRepItemEqu(repItemEquList);
    }

    @Override
    public void updateOneRepItemEqu(RepItemEqu repItemEqu) {
        repItemEquMapper.updateOneRepItemEqu(repItemEqu);
    }

}
