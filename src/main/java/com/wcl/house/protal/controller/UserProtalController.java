package com.wcl.house.protal.controller;

import com.wcl.house.entity.Users;
import com.wcl.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 11:41 2019/8/11
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
@RequestMapping("/page")
public class UserProtalController {
    @Autowired
    private UserService userService;
    @RequestMapping("/name")
    @ResponseBody
    public String getName(String name){
        int i = userService.selectCountByUserName(name);
        return "{\"result\":"+i+"}";
    }

    @RequestMapping("/new")
    @ResponseBody
    public String addNewUser(String name,String password,String telephone){

        int i = userService.insertNewUser(name,password,telephone);
        return "{\"result\":"+i+"}";
    }

    /*
    实现登录功能
     */
    @RequestMapping("/login")
    public String selectUser(String name, String password, Model model){
        Users users = userService.selectByLogin(name, password);
        if (users!=null){
            return "/guanli"; //跳转至发布信息
        }else {
            return "/login";//重新登录
        }
    }
}
