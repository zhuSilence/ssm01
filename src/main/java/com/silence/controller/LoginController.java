package com.silence.controller;

import com.silence.exception.CustomException;
import com.silence.po.User;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * 初始index页面
     * @return
     */
    @RequestMapping(value = "index.action")
    public String index(){
        return "index";
    }

    /**
     * 用户登录方法
     */
    @RequestMapping(value = "/login.action")
    public String login(User user, Model model) throws Exception{
        User user1 = userService.getUserByUsernameAndPassword(user);
        if (user1 == null){
            throw new CustomException("不存在该用户的信息");
        }else {
            if(user1.getLocked()){
                  throw new CustomException("该账户已被锁定，请联系管理员!");
            }
        }
        model.addAttribute("user", user);
        return "home";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "loginOut.action")
    public String loginOut(){

        return "index";
    }



}
