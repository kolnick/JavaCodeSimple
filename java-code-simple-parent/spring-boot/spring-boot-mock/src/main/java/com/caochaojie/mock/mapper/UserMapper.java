package com.caochaojie.mock.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caochaojie.mock.mapper.result.UserResult;
import com.caochaojie.mock.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caochaojie
 * @Date 2022/7/31
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<UserResult> listAllUser();


}
