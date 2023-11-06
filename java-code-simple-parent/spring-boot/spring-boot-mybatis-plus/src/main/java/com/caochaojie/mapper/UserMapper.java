package com.caochaojie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caochaojie.mapper.result.TwoLeftJoin;
import com.caochaojie.mapper.result.UserItemResult;
import com.caochaojie.mapper.result.UserResult;
import com.caochaojie.param.ParamBO;
import com.caochaojie.po.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author caochaojie
 * @Date 2022/7/31
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    Set<Integer> existsUsers(@Param("params") Set<Integer> params);

    List<UserResult> listAllUser();

    List<UserResult> like(@Param("com/caochaojie/param") String userName);

    List<TwoLeftJoin> queryTwoLeft();

    List<UserResult> listAllUserOrder(@Param("com/caochaojie/param") ParamBO paramBO);

    List<UserResult> listAllUserLimit(ParamBO paramBO);

    List<UserItemResult> itemWithCollection();

    @Select("select distinct name from user where name is not null")
    List<String> queryNames();

    User findWithChoose(@Param("name") String name);

    @Select("select count(1) from user_info where create_time between #{startTime} and #{endTime} ")
    long findUserListByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<User> suffixOverrides();

    @MapKey("name")
    Map<String, User> getAllUser();

    int batchUpdate(@Param("id") long id);


    int batchUpdateList(List<Long> list);

}
