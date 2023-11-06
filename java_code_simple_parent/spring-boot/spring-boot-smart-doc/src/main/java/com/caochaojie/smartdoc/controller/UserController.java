package com.caochaojie.smartdoc.controller;

import com.caochaojie.smartdoc.base.BaseResult;
import com.caochaojie.smartdoc.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResult<User> postUser(User user) {
        users.put(user.getId(), user);
        return BaseResult.successWithData(user);
    }

    /**
     * 获取某个用户
     *
     * @param id 用户ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseResult<User> putUser(@PathVariable Long id, User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return BaseResult.successWithData(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @RequestMapping(value = "/ignoreMe/{id}", method = RequestMethod.DELETE)
    public String ignoreMe(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}