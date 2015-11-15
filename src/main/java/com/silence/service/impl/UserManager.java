package com.silence.service.impl;

import com.silence.mapper.UserMapper;
import com.silence.po.User;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhuxiang on 2015/11/13.
 * Desc :
 */
@Service
@Transactional
public class UserManager implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id) throws Exception {
        User user = userMapper.findUserById(id);
        if(user != null){
            return user;
        }
        return null;
    }
}
