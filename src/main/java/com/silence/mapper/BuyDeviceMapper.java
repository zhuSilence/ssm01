package com.silence.mapper;

import com.silence.po.BuyDevice;

public interface BuyDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyDevice record);

    int insertSelective(BuyDevice record);

    BuyDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyDevice record);

    int updateByPrimaryKey(BuyDevice record);
}