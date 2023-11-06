package com.caochaojie;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caochaojie.bo.UserBO;
import com.caochaojie.mapper.UserMapper;
import com.caochaojie.mapper.result.TwoLeftJoin;
import com.caochaojie.mapper.result.UserItemResult;
import com.caochaojie.mapper.result.UserResult;
import com.caochaojie.param.ParamBO;
import com.caochaojie.po.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author caochaojie
 * @Date 2022/8/4
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestCRUD extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private SqlSessionFactory sessionFactory;

    @Test
    public void add() {
        save(User.builder().name("test").createTime(new Date()).build());
    }

    @Test
    public void testMapper() {
        Set<Integer> integers = userMapper.existsUsers(Sets.newHashSet(1, 2, 3, 4, 5, 65, 6));
        log.info("{}", integers);
    }

    @Test
    public void queryListMap() {
        List<UserResult> maps = userMapper.listAllUser();
        for (UserResult map : maps) {
            log.info("{}", map);
        }
    }

    @Test
    public void likeQuery() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList("166", "266", "366", "66"));
        System.out.println(users.size());
    }

    @Test
    public void find() {
        List<UserItemResult> userResults = userMapper.itemWithCollection();
        for (UserItemResult result : userResults) {
            log.info("{}", result);
        }
    }

    @Test
    public void queryTwoLeft() {
        List<TwoLeftJoin> twoLeftJoins = userMapper.queryTwoLeft();
        for (TwoLeftJoin twoLeftJoin : twoLeftJoins) {
            log.info("{}", twoLeftJoin);
        }
    }

    @Test
    public void queryOrder() {
        ParamBO param = new ParamBO();
        Order order = new Order();
        order.setField("id");
        order.setDirection(Direction.ASC);
        param.setOrder(order);
        List<UserResult> userResults = userMapper.listAllUserOrder(param);
        for (UserResult userResult : userResults) {
            log.info("{}", userResult);
        }
    }

    @Test
    public void queryLimit() {
        ParamBO param = new ParamBO();
        param.setPageNumber(1);
        param.setPageSize(2);
        List<UserResult> userResults = userMapper.listAllUserLimit(param);
        for (UserResult userResult : userResults) {
            log.info("{}", userResult);
        }
    }

    @Test
    public void selectDistinctName() {
        List<String> nameList = userMapper.queryNames();
        log.info("{}", nameList);
    }


    @Test
    public void testPage() {
        Page<String> page = PageHelper.startPage(1, 5, true);
        log.info("{}", page);
        List<String> strings = userMapper.queryNames();
        PageInfo pageInfo = new PageInfo(strings);
        log.info("{}", pageInfo);
        PageInfo<String> stringPageInfo = list2PageInfo(strings, 1, 5);
        log.info("{}", stringPageInfo);
    }


    public static <T> PageInfo<T> list2PageInfo(List<T> arrayList, Integer pageNum, Integer pageSize) {
        //实现list分页
        PageHelper.startPage(pageNum, pageSize);
        int pageStart = pageNum == 1 ? 0 : (pageNum - 1) * pageSize;
        int pageEnd = arrayList.size() < pageSize * pageNum ? arrayList.size() : pageSize * pageNum;
        List<T> pageResult = new LinkedList<T>();
        if (arrayList.size() > pageStart) {
            pageResult = arrayList.subList(pageStart, pageEnd);
        }
        PageInfo<T> pageInfo = new PageInfo<T>(pageResult);
        //获取PageInfo其他参数
        pageInfo.setTotal(arrayList.size());
        int endRow = pageInfo.getEndRow() == 0 ? 0 : (pageNum - 1) * pageSize + pageInfo.getEndRow() + 1;
        pageInfo.setEndRow(endRow);
        boolean hasNextPage = arrayList.size() <= pageSize * pageNum ? false : true;
        pageInfo.setHasNextPage(hasNextPage);
        boolean hasPreviousPage = pageNum == 1 ? false : true;
        pageInfo.setHasPreviousPage(hasPreviousPage);
        pageInfo.setIsFirstPage(!hasPreviousPage);
        boolean isLastPage = (arrayList.size() > pageSize * (pageNum - 1) && arrayList.size() <= pageSize * pageNum) ? true : false;
        pageInfo.setIsLastPage(isLastPage);
        int pages = arrayList.size() % pageSize == 0 ? arrayList.size() / pageSize : (arrayList.size() / pageSize) + 1;
        pageInfo.setNavigateLastPage(pages);
        int[] navigatePageNums = new int[pages];
        for (int i = 1; i < pages; i++) {
            navigatePageNums[i - 1] = i;
        }
        pageInfo.setNavigatepageNums(navigatePageNums);
        int nextPage = pageNum < pages ? pageNum + 1 : 0;
        pageInfo.setNextPage(nextPage);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPages(pages);
        pageInfo.setPrePage(pageNum - 1);
        pageInfo.setSize(pageInfo.getList().size());
        int starRow = arrayList.size() < pageSize * pageNum ? 1 + pageSize * (pageNum - 1) : 0;
        pageInfo.setStartRow(starRow);
        return pageInfo;
    }

    @Test
    public void testPageHelp() {
        Page<String> page = PageHelper.startPage(1, 5, true);
        log.info("{}", page);
        page.setOrderBy("id desc ");
        List<UserResult> userResults = userMapper.listAllUser();
        log.info("{}", userResults);
    }

    @Test
    public void testOr() {
        QueryWrapper<User> userLambdaQueryWrapper = new QueryWrapper<>();
        userLambdaQueryWrapper.eq("name", "12");
        userLambdaQueryWrapper.eq("name", "牛2");
        userLambdaQueryWrapper.or(Wrapper -> Wrapper.eq("sex", "男2"));
        Map<String, Object> paramNameValuePairs = userLambdaQueryWrapper.getParamNameValuePairs();
        log.info("params{}", paramNameValuePairs);
        List<User> list = list(userLambdaQueryWrapper);
        log.info("{}", list);
    }

    @Test
    public void lambdaWithLambdaQuery() {
        UserBO userBO = UserBO.builder().age(11).name("1").build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("count(1) count")
                .lambda().ne(userBO.getAge() > 0, User::getName, "2")
                .like(StringUtils.hasLength(userBO.getName()), User::getName, userBO.getName());
        List<User> list = list(queryWrapper);
        log.info("{}", list);
    }

    @Test
    public void testQueryChoose() {
        User user = userMapper.findWithChoose("a2");
        log.info("{}", user);
    }

    @Test
    public void testColumnMap() {
        Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(User.class);
        for (Map.Entry<String, ColumnCache> entry : columnMap.entrySet()) {
            String key = entry.getKey();
            ColumnCache columnCache = entry.getValue();
            log.info("column key:{}----column:{} column mapping:{} columnSelect:{}", key, columnCache.getColumn(), columnCache.getMapping(), columnCache.getColumnSelect());
        }
    }

    @Test
    public void testFindAll() {
        List<User> list = list();
        log.info("{}", list);
    }


    @Test
    public void testDateFind() {
        DateTime startTime = DateUtil.parseDate("2022-12-15 12:17:12");
        DateTime endTime = DateUtil.parseDate("2022-12-16 00:00:00");
        Date date = startTime.toJdkDate();
        Date date1 = endTime.toJdkDate();
        long userListByDate = userMapper.findUserListByDate(date, date1);
        log.info("{}", userListByDate);
    }

    @Test
    public void suffixOverrides() {
        List<User> users = userMapper.suffixOverrides();
        log.info("{}", users);
    }


    @Test
    public void testMapKey() {
        Map<String, User> allUser = userMapper.getAllUser();
        log.info("{}", allUser);
    }

    @Test
    // @Transactional(rollbackFor = Exception.class)
    public void batchUpdate() throws IOException {
        File file = resourceLoader.getResource("classpath:data").getFile();
        List<String> strings = FileUtil.readLines(file, Charset.forName("UTF-8"));
        List<Long> list = strings.stream().map(Long::valueOf).collect(Collectors.toList());


        BiFunction<Long, UserMapper, Object> longUserMapperObjectBiFunction = (x, y) -> {
            return y.batchUpdate(x);
        };
        batchUpdateOrInsert(list, UserMapper.class, longUserMapperObjectBiFunction);
    }


    @Test
    // @Transactional(rollbackFor = Exception.class)
    public void batchUpdateList() throws IOException {
        File file = resourceLoader.getResource("classpath:data").getFile();
        List<String> strings = FileUtil.readLines(file, Charset.forName("UTF-8"));
        List<Long> list = strings.stream().map(Long::valueOf).collect(Collectors.toList());
        long s1 = System.currentTimeMillis();
        userMapper.batchUpdateList(list);
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }


    /**
     * 批量处理修改或者插入
     *
     * @param data        需要被处理的数据
     * @param mapperClass Mybatis的Mapper类
     * @param function    自定义处理逻辑
     * @return int 影响的总行数
     */
    public <T, U, R> int batchUpdateOrInsert(List<T> data, Class<U> mapperClass, BiFunction<T, U, R> function) {
        int i = 1;
        int BATCH_SIZE = 1000;
        SqlSession batchSqlSession = sessionFactory.openSession(ExecutorType.BATCH);
        try {
            U mapper = batchSqlSession.getMapper(mapperClass);
            int size = data.size();
            long s1 = System.currentTimeMillis();
            for (T element : data) {
                function.apply(element, mapper);
                if ((i % BATCH_SIZE == 0) || i == size) {
                    batchSqlSession.commit();
                    //清理缓存，防止溢出
                    batchSqlSession.clearCache();
                }
                i++;
            }
            long s2 = System.currentTimeMillis();
            System.out.println(s2 - s1);
            // 非事务环境下强制commit，事务情况下该commit相当于无效
            // batchSqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
        } catch (Exception e) {
            batchSqlSession.rollback();
        } finally {
            batchSqlSession.close();
        }
        return i - 1;
    }
}
