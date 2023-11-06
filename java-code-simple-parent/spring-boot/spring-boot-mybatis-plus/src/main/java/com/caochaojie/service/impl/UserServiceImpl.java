package com.caochaojie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caochaojie.mapper.UserMapper;
import com.caochaojie.po.User;
import com.caochaojie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xushihao
 * @since 2021-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public UserServiceImpl() {
        System.out.println("无参构造调用了！");
    }
}
