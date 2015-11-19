package com.silence.mapper;

import com.silence.po.FixDevice;

public interface FixDeviceMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(FixDevice record) throws Exception;

    int insertSelective(FixDevice record) throws Exception;

    FixDevice selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(FixDevice record) throws Exception;

    int updateByPrimaryKey(FixDevice record) throws Exception;
}