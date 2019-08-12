package com.wcl.house.mapper;

import com.wcl.house.entity.Users;
import com.wcl.house.entity.UsersExample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    /**
     * 查询用户名是否存在
     * @param name
     * @return
     */
    @Select("SELECT COUNT(*) FROM users WHERE NAME=#{0}")
    int selectCountByUserName(String name);


    @Insert("insert into users (id,name,password,telephone,isadmin) values (null,#{0},#{1},#{2},0) ")
    int insertNewUser(String name,String password,String telephone);

    /*
    * 登录判断*/
    @Select("select from users where name=#{0},password=#{1},isadmin=0")
    Users selectByLogin(String name,String password);
}