package com.caochaojie.sqllite;

import org.junit.Test;

import java.sql.*;

public class SqliteTest {

    @Test
    public void testInsert() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:test");
            Statement statement = conn.createStatement();
            //stmt.executeQuery("")
            //stmt.executeUpdate("INSERT INTO item VALUES(true)");

            ResultSet rsExist = statement.executeQuery("SELECT * FROM sqlite_master where type='table' and name ='employee';"); //查询employee表是否存在
            if (!rsExist.next()) {
                statement.executeUpdate("create table employee(name varchar(20), salary int);");//创建一个表，两列
                statement.executeUpdate("insert into employee values('张三',8000);"); //插入数据
                statement.executeUpdate("insert into employee values('李四',7800);");
                statement.executeUpdate("insert into employee values('王五',5800);");
                statement.executeUpdate("insert into employee values('赵六',9100);");
            } else {
                while (rsExist.next()) {
                    String name = rsExist.getString("name");
                    int salary = rsExist.getInt("salary");
                    System.out.println("name:" + name + "," + "salary:" + salary);
                }
            }
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
