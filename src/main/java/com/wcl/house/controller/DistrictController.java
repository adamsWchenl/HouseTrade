package com.wcl.house.controller;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.District;
import com.wcl.house.entity.Street;
import com.wcl.house.service.DistrictService;
import com.wcl.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 8:51 2019/8/9
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
@RequestMapping("/admin")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;

    //查询所有的街道
    @RequestMapping("/getStreet")
    @ResponseBody
    public Map<String,Object> getStreet(Integer page,Integer rows,Integer id){
        //调用业务
        PageInfo<Street> pageInfo = streetService.getStreetByPage(page,rows,id);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //查询所有
    @RequestMapping("/getDistrict")
    @ResponseBody
    public Map<String,Object> getDistrict(Integer page,Integer rows){
       //调用业务
        PageInfo<District> pageInfo = districtService.getDistrictByPage(page, rows);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    //新增区域
    @RequestMapping("/addDistrict")
    @ResponseBody
    public String addDistrict(District district){
        int i = districtService.addDistrict(district);
        return "{\"result\":"+i+"}";
    }
    //修改区域
    @RequestMapping("/updateDistrict")
    @ResponseBody
    public String updateDistrict(District district){
        int i = districtService.updateDistrict(district);
        return "{\"result\":"+i+"}";
    }

    /**
     * 删除区域的同时需要删除区域中的街道
     */
    @RequestMapping("/deleteDistrict")
    @ResponseBody
    public String deleteDistrict(Integer id){
        try {
            streetService.modifyDistrictStreet(id);
            districtService.deleteDistrict(id);
            return "{\"result\":"+1+"}";
        }catch (Exception e){
            return "{\"result\":"+0+"}";
        }
    }

    @RequestMapping("/delBatch")
    @ResponseBody
    public String balDeleteDistrict(String ids){
        //将字符串转换成数组
        String[] split = ids.split(",");
        Integer[] integers = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            integers[i] = Integer.parseInt(split[i]);
        }
        //调用业务
        //同时也需要删除区域里面的街道
        try {
        streetService.deleteMoreDistrictStreet(integers);
        districtService.deleteMoreDistrict(integers);
            return "{\"result\":"+1+"}";
        }catch (Exception e){
            return "{\"result\":"+0+"}";
        }
    }
}
