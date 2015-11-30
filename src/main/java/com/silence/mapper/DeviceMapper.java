package com.silence.mapper;

import com.silence.po.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Device record);

    int insertSelective(Device record) throws Exception;

    Device selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(Device record) throws Exception;

    int updateByPrimaryKey(Device record) throws Exception;
}