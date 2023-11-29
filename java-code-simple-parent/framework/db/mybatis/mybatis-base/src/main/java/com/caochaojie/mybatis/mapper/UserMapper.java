package com.caochaojie.mybatis.mapper;


import com.caochaojie.mybatis.bean.User;

import java.util.List;

/**
 * Created by sang on 17-1-13.
 */
public interface UserMapper {
    List<User> getUser();

    int insertUser(User user);

}
