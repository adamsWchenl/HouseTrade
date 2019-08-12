package com.wcl.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.District;
import com.wcl.house.entity.DistrictExample;
import com.wcl.house.mapper.DistrictMapper;
import com.wcl.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 8:43 2019/8/9
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
@Service
@Transactional(readOnly = false)
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public PageInfo<District> getDistrictByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        DistrictExample example = new DistrictExample();
        List<District> list = districtMapper.selectByExample(example);
        return new PageInfo<District>(list);
    }

    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKey(district);
        //return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public int deleteDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreDistrict(Integer[] integers) {
        return districtMapper.deleteMoreDistrict(integers);
    }

    @Override
    public List<District> findDistrict() {
        DistrictExample example = new DistrictExample();
        return districtMapper.selectByExample(example);
    }

}
