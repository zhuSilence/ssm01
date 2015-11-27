package com.silence.service;

import com.silence.po.User;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;

import java.util.List;
import java.util.Map;

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
     * 查询出所有的用户信息
     * @return
     * @throws Exception
     */
    public List<User> getUserList(Map<String, Object> map, Pageable pageable) throws Exception;

    /**
     * 查询出所有的用户记录条数
     * @return
     * @throws Exception
     */
    public int getUserListSize(Map<String, Object> map, Pageable pageable) throws Exception;

    /**
     * 根据用户信息插入用户实体
     */
    public String insertUser(Map<String,Object> map) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @throws Exception
     */
    public void updateUser(Map<String,Object> map) throws Exception;

    /**
     * 根据用户名和密码查询用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public User getUserByUsernameAndPassword(User user) throws Exception;

    /**
     * 根据传入的用户的id，删除用户的信息
     * @param id
     * @throws Exception
     */
    public Integer deleteUserById(Integer id) throws Exception;
}
