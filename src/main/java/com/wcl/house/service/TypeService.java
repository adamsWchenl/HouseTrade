package com.wcl.house.service;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.Type;

import java.util.List;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 8:41 2019/8/9
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public interface TypeService {
    //查询所有的区域
    PageInfo<Type> getTypeByPage(Integer page, Integer pageSize);

    /**
     * 新增区域
     * @param
     *
     */
    int addType(Type type);

    /**
     * 修改区域
     * @param
     */
    int updateType(Type type);

    /**
     * 删除区域
     * @param id
     */
    int deleteType(Integer id);

    int deleteMoreType(Integer[] integers);

    List<Type> findType();

}
