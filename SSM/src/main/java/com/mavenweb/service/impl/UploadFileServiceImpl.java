package com.mavenweb.service.impl;

import com.mavenweb.domain.UploadFile;
import com.mavenweb.mapper.UploadFileMapper;
import com.mavenweb.service.UploadFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Resource
    UploadFileMapper uploadFileMapper ;

    @Override
    public int deleteByPrimaryKey(Integer fileId) {
        return uploadFileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public int insert(UploadFile uploadFile) {
        return uploadFileMapper.insert(uploadFile);
    }

    @Override
    public int insertSelective(UploadFile upload) {
        return uploadFileMapper.insertSelective(upload);
    }

    @Override
    public UploadFile selectByPrimaryKey(Integer fileId) {
        return uploadFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int updateByPrimaryKeySelective(UploadFile upload) {
        return uploadFileMapper.updateByPrimaryKeySelective(upload);
    }

    @Override
    public int updateByPrimaryKey(UploadFile upload) {
        return uploadFileMapper.updateByPrimaryKey(upload);
    }

    @Override
    public Integer insertOneUploadedFile(UploadFile uploadFile) {
        return uploadFileMapper.insertOneUploadedFile(uploadFile);
    }
}
