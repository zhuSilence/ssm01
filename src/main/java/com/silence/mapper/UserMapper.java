package com.silence.mapper;

import com.silence.po.User;
import com.silence.vo.UserQueryVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id) throws Exception;

    void insert(User record) throws Exception;

    int insertSelective(User record) throws Exception;

    User selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(User record) throws Exception;

    int updateByPrimaryKey(User record) throws Exception;

}