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

    //根据用户的id查询用户信息
    public User getUserById(Integer id) throws Exception {
        User user = userMapper.selectByPrimaryKey(id);
            return user;
    }

    //根据用户信息插入用户实体
    public void insertUser(User user) throws Exception{
        if(user != null){
            userMapper.insert(user);
        }
    }

    /**
     * 修改用户信息
     * @param user
     * @throws Exception
     */
    public void updateUser(User user) throws Exception{
        if (user != null) {
            userMapper.updateByPrimaryKey(user);
        }
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public User getUserByUsernameAndPassword(User user) throws Exception{
        User user1 = userMapper.selectByUsernameAndPassword(user);
        if (user1 != null) {
            return user1;
        }else {
            return null;
        }
    }
}
