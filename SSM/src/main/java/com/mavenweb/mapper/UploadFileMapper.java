package com.mavenweb.mapper;

import com.mavenweb.domain.UploadFile;

public interface UploadFileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);

    Integer insertOneUploadedFile(UploadFile uploadFile);
}