package com.wcl.house.service.impl;

import com.wcl.house.entity.House;
import com.wcl.house.mapper.HouseMapper;
import com.wcl.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 16:34 2019/8/12
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Service
@Transactional(readOnly = false)
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addNewHouse(House house) {
        int i = houseMapper.insertSelective(house);
        return i;
    }
}
