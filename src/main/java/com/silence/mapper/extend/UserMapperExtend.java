package com.silence.mapper.extend;

import com.silence.mapper.UserMapper;
import com.silence.po.User;
import com.silence.vo.UserQueryVo;

import java.util.List;

/**
 * Created by zhuxiang on 2015/11/30.
 * Desc :
 */
public interface UserMapperExtend extends UserMapper {

    //根据用户名查询用户信息
    User findUserByUsername(String username) throws Exception;

    //根据用户名和密码查询用户信息，用于登录
    User selectByUsernameAndPassword(User user) throws Exception;

    //根据页面用户的查询条件动态查询用户信息列表
    List<User> selectUserList(UserQueryVo userQueryVo) throws Exception;

    //根据页面用户的查询条件动态查询用户信息列表的长度，用户分页
    int selectAllUserListSize(UserQueryVo userQueryVo) throws Exception;
}
