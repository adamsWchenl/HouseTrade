package com.wcl.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.Type;
import com.wcl.house.entity.TypeExample;
import com.wcl.house.mapper.TypeMapper;
import com.wcl.house.service.TypeService;
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
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getTypeByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        TypeExample example = new TypeExample();
        List<Type> list = typeMapper.selectByExample(example);
        return new PageInfo<Type>(list);
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKey(type);
        //return typeMapper.updateByPrimaryKeySelective(Type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreType(Integer[] integers) {
        return typeMapper.deleteMoreType(integers);
    }

    @Override
    public List<Type> findType() {
        TypeExample example = new TypeExample();
        List<Type> types = typeMapper.selectByExample(example);
        return types;
    }

}
