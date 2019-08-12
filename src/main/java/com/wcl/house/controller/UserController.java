package com.wcl.house.controller;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.UserCondition;
import com.wcl.house.entity.Users;
import com.wcl.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 20:44 2019/8/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String,Object> getUserInfo(UserCondition condition){
        //调用业务
        PageInfo<Users> pageInfo=userService.getUserByPage(condition);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
