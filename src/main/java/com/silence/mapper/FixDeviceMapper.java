package com.silence.mapper;

import com.silence.po.FixDevice;

public interface FixDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FixDevice record);

    int insertSelective(FixDevice record);

    FixDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FixDevice record);

    int updateByPrimaryKey(FixDevice record);
}