package com.silence.mapper;

import com.silence.po.BuyDevice;

public interface BuyDeviceMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(BuyDevice record) throws Exception;

    int insertSelective(BuyDevice record) throws Exception;

    BuyDevice selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(BuyDevice record) throws Exception;

    int updateByPrimaryKey(BuyDevice record) throws Exception;
}