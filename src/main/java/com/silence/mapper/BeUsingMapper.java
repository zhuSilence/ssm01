package com.silence.mapper;

import com.silence.po.BeUsing;

public interface BeUsingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BeUsing record);

    int insertSelective(BeUsing record);

    BeUsing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BeUsing record);

    int updateByPrimaryKey(BeUsing record);
}