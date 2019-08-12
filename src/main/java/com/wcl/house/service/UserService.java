package com.wcl.house.service;

import com.github.pagehelper.PageInfo;
import com.wcl.house.entity.UserCondition;
import com.wcl.house.entity.Users;

/**
 * @ Author     ：王辰亮.
 * @ Date       ：Created in 20:43 2019/8/10
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public interface UserService {
    PageInfo<Users> getUserByPage(UserCondition condition);

    int selectCountByUserName(String name);

    /**
     * 注册新用户
     *
     * @return
     */
    int insertNewUser(String name,String password,String telephone);

    Users selectByLogin(String name, String password);
}
