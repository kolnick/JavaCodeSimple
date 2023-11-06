package com.caochaojie.mock.service;

import com.caochaojie.mock.po.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {

    User findById(Serializable id);

    List<User> getAllUser();
}
