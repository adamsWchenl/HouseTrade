package com.wcl.house.service;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.Street;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 14:39 2019/8/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public interface StreetService {
    /**
     * 删除区域中的街道
     * @param id
     * @return
     */
    int modifyDistrictStreet(Integer id);

    int deleteMoreDistrictStreet(Integer[] integers);

    PageInfo<Street> getStreetByPage(Integer page, Integer rows,Integer id);

    List<Street> getStreet(Integer did);
}
