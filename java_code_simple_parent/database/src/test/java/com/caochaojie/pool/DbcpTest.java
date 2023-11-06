package com.caochaojie.pool;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import com.caochaojie.druid.DbPoolConnection;
import org.junit.Test;

import java.util.List;

public class DbcpTest {

    private static final String addItem = "insert into bag (count) values(?)";
    private static final String updateItem = "update  bag  set count=? where item=?";
    private static final String select = "select *  from item";

    private static DbPoolConnection dbp = DbPoolConnection.getInstance();

    @Test
    public void parseSQL() {
        String dbType = JdbcConstants.MYSQL;
        List<SQLStatement> statementList = SQLUtils.parseStatements(select, dbType);
        System.out.println(statementList);
        SQLStatement sqlStatement = statementList.get(0);
    }



}
