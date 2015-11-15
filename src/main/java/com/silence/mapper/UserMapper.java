package com.silence.mapper;

import com.silence.po.User;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
public interface UserMapper {
    //根据用户的id查询用户对象
    User findUserById(Integer id) throws Exception;
}
