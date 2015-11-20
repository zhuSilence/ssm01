package com.silence.controller;

import com.silence.exception.CustomException;
import com.silence.po.User;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Created by zhuxiang on 2015/11/13.
 * Desc : 系统登录controller
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录方法
     */
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(User user, Model model) throws Exception{

        User user1 = userService.getUserByUsernameAndPassword(user);
        if (user1 == null){
            throw new CustomException("不存在该用户的信息");
        }else {
            if(user1.getLocked()){
                  throw new CustomException("该账户已被锁定，请联系管理员!");
            }
        }
        model.addAttribute("username", user1.getUsername());
        return "home";
    }



}
