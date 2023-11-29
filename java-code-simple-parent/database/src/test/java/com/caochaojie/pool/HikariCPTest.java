package com.caochaojie.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: caochaojie
 * @Date: 2023-11-22 20:08
 */
public class HikariCPTest {


    @Test
    public void test() {
        // 设置HikariCP连接池配置
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:7766/game?serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("123456");
// 初始化连接池
        HikariDataSource dataSource = new HikariDataSource(config);

        try {
            // 从连接池获取Connection
            Connection connection = dataSource.getConnection();
            if (!connection.isClosed()) {
                System.out.println(1);
            }
            // 释放连接
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 在finally中关闭连接池
            dataSource.close();
        }
    }

}
