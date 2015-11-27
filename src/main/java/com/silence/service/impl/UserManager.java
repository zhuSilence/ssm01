package com.silence.service.impl;

import com.silence.exception.CustomException;
import com.silence.mapper.UserMapper;
import com.silence.po.User;
import com.silence.service.UserService;
import com.silence.utils.DateTool;
import com.silence.utils.MD5Util;
import com.silence.utils.Pageable;
import com.silence.vo.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    /**
     * 查询出所有的用户信息
     * @return
     * @throws Exception
     */
    public List<User> getUserList(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setMap(map);
        userQueryVo.setPageable(pageable);
        List<User> userList = userMapper.selectUserList(userQueryVo);
        if (userList != null && userList.size() > 0){
            return userList;
        }else {
            return null;
        }
    }

    /**
     * 查询出所有的用户记录条数
     * @return
     * @throws Exception
     */
    public int getUserListSize(Map<String, Object> map, Pageable pageable) throws Exception{
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setPageable(pageable);
        userQueryVo.setMap(map);
        Integer userListSize = userMapper.selectAllUserListSize(userQueryVo);
        if(userListSize != 0){
            return userListSize;
        }
        return 0;
    }

    //根据用户信息插入用户实体
    public String insertUser(Map<String,Object> map) throws Exception{
        if(map != null){
            User user = new User();
            if(map.containsKey("row[username]") && !map.get("row[username]").toString().trim().equals("")){
                User user1 = userMapper.findUserByUsername(map.get("row[username]").toString().trim());
                if(user1 != null){
                    return "该用户名称已存在，请重新添加";
                }
                user.setUsername(map.get("row[username]").toString().trim());
            }
            if(map.containsKey("row[password]") && !map.get("row[password]").toString().trim().equals("")){
                if(map.containsKey("row[salt]") && !map.get("row[salt]").toString().trim().equals("")) {
                    user.setSalt(map.get("row[salt]").toString().trim());
                    user.setPassword(MD5Util.MD5(map.get("row[password]").toString().trim()
                            + map.get("row[salt]").toString().trim()));
                }else {
                    user.setPassword(MD5Util.MD5(MD5Util.MD5(map.get("row[password]").toString().trim())));
                }
            }
            if(map.containsKey("row[date]")){
                Date date = DateTool.standardStringToDate(map.get("row[date]").toString(),"yyyy-MM-dd HH:mm:ss");
                user.setDate(date);
            }

            if(map.containsKey("row[locked]")){
                user.setLocked(Boolean.TRUE);
            }else {
                user.setLocked(Boolean.FALSE);
            }
            userMapper.insertSelective(user);
        }
        return "success";
    }

    /**
     * 修改用户信息
     * @param map
     * @throws Exception
     */
    public void updateUser(Map<String,Object> map) throws Exception{
        if(map != null && map.containsKey("row[id]")){
            User user = userMapper.selectByPrimaryKey(Integer.parseInt(map.get("row[id]").toString()));
            if(user != null){
                if(map.containsKey("row[username]") && !map.get("row[username]").toString().trim().equals("")){
                    user.setUsername(map.get("row[username]").toString());
                }
                if(map.containsKey("row[password]") && !map.get("row[password]").toString().trim().equals("")){
                    if(map.containsKey("row[salt]") && !map.get("row[salt]").toString().trim().equals("")){
                        if(!map.get("row[password]").toString().trim().equals(user.getPassword())){
                            user.setPassword(MD5Util.MD5(map.get("row[password]").toString().trim()
                                    + user.getSalt()));
                        }
                    }
                }
                if(map.containsKey("row[date]")){
                    Date date = DateTool.timestampToDate(map.get("row[date]").toString());
                    user.setDate(date);
                }

                if(map.containsKey("row[locked]")){
                    user.setLocked(Boolean.TRUE);
                }else {
                    user.setLocked(Boolean.FALSE);
                }
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
    }

    /**
     * 根据用户名和密码查询用户信息
     * @param user
     * @return
     * @throws Exception
     */
    public User getUserByUsernameAndPassword(User user) throws Exception{
        User user1 = userMapper.findUserByUsername(user.getUsername().trim());
        if(user1 != null){
            if(user1.getPassword().equals(MD5Util.MD5(user.getPassword()+user1.getSalt()))){
                return user1;
            }else {
                return null;
            }
        }else {
            throw new CustomException("不存在该用户的信息!");
        }
    }

    /**
     * 根据传入的用户的id，删除用户的信息
     * @param id
     * @throws Exception
     */
    public Integer deleteUserById(Integer id) throws Exception{
        int rows = userMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return rows;
        }
        return 0;
    }
}
