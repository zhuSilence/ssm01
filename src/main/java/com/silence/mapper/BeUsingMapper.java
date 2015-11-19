package com.silence.mapper;

import com.silence.po.BeUsing;

public interface BeUsingMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(BeUsing record) throws Exception;

    int insertSelective(BeUsing record) throws Exception;

    BeUsing selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(BeUsing record) throws Exception;

    int updateByPrimaryKey(BeUsing record) throws Exception;
}