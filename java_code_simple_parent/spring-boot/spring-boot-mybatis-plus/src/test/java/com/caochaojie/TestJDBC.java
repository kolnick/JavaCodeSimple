package com.caochaojie;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author caochaojie
 * 2022/12/7
 * @description
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class TestJDBC {

    @Autowired
    DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String select = "select *  from item";


    @Test
    public void test() {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet resultSet = ps.executeQuery();
            int size = 0;
            resultSet.close();
            ps.close();
            PreparedStatement ps2 = con.prepareStatement(select);
            ResultSet resultSet2 = ps2.executeQuery();
            while (resultSet2.next()) {
                String id = resultSet2.getString(1);
                String name = resultSet2.getString(2);
                System.out.println("id:" + id + "name:" + name);
                size++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void batchUpdate() throws IOException {
        File file = resourceLoader.getResource("classpath:data").getFile();
        List<String> strings = FileUtil.readLines(file, Charset.forName("UTF-8"));
        List<Long> list = strings.stream().map(Long::valueOf).collect(Collectors.toList());
        String sql = "update ta_result_object  set bak_record = 1 where uuid = ?";
        long s1 = System.currentTimeMillis();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            // 开启事务
            con.setAutoCommit(false);

            // 批量更新语句
            // 批量设置参数
            for (int i = 0; i < 10000; i++) {
                ps.setLong(1, list.get(i));
                ps.addBatch();
            }
            // 批量执行更新操作
            int[] updateCounts = ps.executeBatch();
            // 提交事务
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
