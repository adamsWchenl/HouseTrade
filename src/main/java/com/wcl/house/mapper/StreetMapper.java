package com.wcl.house.mapper;

import com.wcl.house.entity.Street;
import com.wcl.house.entity.StreetExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    //删除区域中的街道
    @Delete("delete  from street where DISTRICT_ID=#{0}")
    int deleteDistrictStreet(Integer id);

    int deleteMoreDistrictStreet(Integer[] integers);

    @Select("select * from street where DISTRICT_ID=#{0}")
    List<Street> selectStreetByDistrictId(Integer id);

}