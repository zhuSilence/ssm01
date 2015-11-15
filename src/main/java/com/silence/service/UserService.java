package com.silence.service;

import com.silence.po.User;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
public interface UserService {
    public User getUserById(Integer id) throws Exception;
}
