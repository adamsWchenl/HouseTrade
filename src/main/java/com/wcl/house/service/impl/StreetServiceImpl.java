package com.wcl.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.Street;
import com.wcl.house.mapper.StreetMapper;
import com.wcl.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 14:40 2019/8/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Service
@Transactional(readOnly = false)
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public int modifyDistrictStreet(Integer id) {
       return streetMapper.deleteDistrictStreet(id);
    }

    @Override
    public int deleteMoreDistrictStreet(Integer[] integers) {
        return streetMapper.deleteMoreDistrictStreet(integers);
    }

    @Override
    public PageInfo<Street> getStreetByPage(Integer page, Integer rows,Integer id) {
        PageHelper.startPage(page,rows);
        List<Street> s = streetMapper.selectStreetByDistrictId(id);
        PageInfo<Street> pageInfo=new PageInfo<>(s);
        return pageInfo;
    }

    @Override
    public List<Street> getStreet(Integer did) {
        List<Street> streets = streetMapper.selectStreetByDistrictId(did);
        return streets;
    }
}
