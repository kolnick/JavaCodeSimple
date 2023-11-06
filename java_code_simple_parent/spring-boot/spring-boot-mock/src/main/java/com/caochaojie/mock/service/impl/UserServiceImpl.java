package com.caochaojie.mock.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caochaojie.mock.mapper.UserMapper;
import com.caochaojie.mock.po.User;
import com.caochaojie.mock.service.UserService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findById(Serializable id) {
        return getById(id);
    }

    @Override
    public List<User> getAllUser() {
        return list();
    }
}
