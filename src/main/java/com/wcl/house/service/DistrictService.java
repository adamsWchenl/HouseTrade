package com.wcl.house.service;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.District;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 8:41 2019/8/9
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public interface DistrictService {
    //查询所有的区域
    PageInfo<District> getDistrictByPage(Integer page, Integer pageSize);

    /**
     * 新增区域
     * @param district
     *
     */
    int addDistrict(District district);

    /**
     * 修改区域
     * @param district
     */
    int updateDistrict(District district);

    /**
     * 删除区域
     * @param id
     */
    int deleteDistrict(Integer id);

    int deleteMoreDistrict(Integer[] integers);

    List<District> findDistrict();

}
