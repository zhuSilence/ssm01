package com.silence.service;

import com.silence.po.User;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
public interface UserService {

    /**
     * 根据用户的id查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(Integer id) throws Exception;

    /**
     * 根据用户信息插入用户实体
     */
    public void insertUser(User user) throws Exception;
}
