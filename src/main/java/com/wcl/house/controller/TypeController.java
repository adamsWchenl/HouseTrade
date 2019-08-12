package com.wcl.house.controller;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.Type;
import com.wcl.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 20:28 2019/8/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    //查询所有
    @RequestMapping("/getType")
    @ResponseBody
    public Map<String,Object> getType(Integer page, Integer rows){
        //调用业务
        PageInfo<Type> pageInfo = typeService.getTypeByPage(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //新增区域
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        int i = typeService.addType(type);
        return "{\"result\":"+i+"}";
    }
    //修改区域
    @RequestMapping("/updatetype")
    @ResponseBody
    public String updatetype(Type type){
        int i = typeService.updateType(type);
        return "{\"result\":"+i+"}";
    }

    /**
     * 删除区域的同时需要删除区域中的街道
     */
    @RequestMapping("/deleteType")
    @ResponseBody
    public String deleteType(Integer id){
        try {
            typeService.deleteType(id);
            return "{\"result\":"+1+"}";
        }catch (Exception e){
            return "{\"result\":"+0+"}";
        }
    }

    @RequestMapping("/delBatch")
    @ResponseBody
    public String balDeleteType(String ids){
        //将字符串转换成数组
        String[] split = ids.split(",");
        Integer[] integers = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            integers[i] = Integer.parseInt(split[i]);
        }
        //调用业务
        //同时也需要删除区域里面的街道
        try {
            typeService.deleteMoreType(integers);
            return "{\"result\":"+1+"}";
        }catch (Exception e){
            return "{\"result\":"+0+"}";
        }
    }
}
