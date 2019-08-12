package com.wcl.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.UserCondition;
import com.wcl.house.entity.Users;
import com.wcl.house.entity.UsersExample;
import com.wcl.house.mapper.UsersMapper;
import com.wcl.house.service.UserService;
import com.wcl.house.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 待条件查询
     * @param condition
     * @return
     */
    @Override
    public PageInfo<Users> getUserByPage(UserCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        UsersExample ex = new UsersExample();
        UsersExample.Criteria criteria = ex.createCriteria();
        criteria.andIsadminEqualTo(0);
        if (condition.getName()!=null){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
       if (condition.getTelephone()!=null){
           criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
       }
        List<Users> users = usersMapper.selectByExample(ex);
        PageInfo<Users> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    public int selectCountByUserName(String name){
        int i = usersMapper.selectCountByUserName(name);
        return i==1?0:1;
    }

    @Override
    public int insertNewUser(String name,String password,String telephone) {
        password= MD5Utils.md5Encrypt(password);
        int i = usersMapper.insertNewUser(name,password,telephone);
        return i;
    }

    @Override
    public  Users selectByLogin(String name, String password) {
        UsersExample ex = new UsersExample();
        password= MD5Utils.md5Encrypt(password);
        UsersExample.Criteria criteria = ex.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        List<Users> users = usersMapper.selectByExample(ex);
        if(users.size()==1){
            return users.get(0);
        }
        return null;
    }
}
