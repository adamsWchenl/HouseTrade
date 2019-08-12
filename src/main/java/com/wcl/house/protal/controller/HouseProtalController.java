package com.wcl.house.protal.controller;

import com.wcl.house.entity.District;
import com.wcl.house.entity.House;
import com.wcl.house.entity.Street;
import com.wcl.house.entity.Type;
import com.wcl.house.service.DistrictService;
import com.wcl.house.service.HouseService;
import com.wcl.house.service.StreetService;
import com.wcl.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 15:01 2019/8/12
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Controller
@RequestMapping("/house")
public class HouseProtalController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;

    //查询所有的房间类型
    @RequestMapping("/gofabu")
    public String getType(Model model){
        List<Type> type=typeService.findType();
        List<District> district=districtService.findDistrict();
        model.addAttribute("type",type);
        model.addAttribute("district",district);
        return "/fabu";
    }
    @RequestMapping("/findStreet")
    @ResponseBody
    public List<Street> getStreet(Integer did){
       List<Street> streets= streetService.getStreet(did);
       return streets;
    }
    @RequestMapping("/addHouse")
    public String addHouse(House house){
        int i=houseService.addNewHouse(house);
        if (i==1){
            return "/guanli";
        }else {
            return "/fabu";
        }
    }
}
