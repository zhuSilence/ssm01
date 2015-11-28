package com.silence.mapper;

import com.silence.po.Nav;

public interface NavMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nav record);

    int insertSelective(Nav record);

    Nav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nav record);

    int updateByPrimaryKey(Nav record);
}