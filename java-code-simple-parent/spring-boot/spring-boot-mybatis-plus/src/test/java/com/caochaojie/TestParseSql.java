package com.caochaojie;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author caochaojie
 * 2022/12/7
 * @description
 */
@Slf4j
public class TestParseSql {

    @Test
    public void test() {
        String dbType = JdbcConstants.MYSQL.name();
        String sql = "select id,name from db";
        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, dbType);
        SQLStatement sqlStatement = statementList.get(0);
        Map<String, Object> attributes = sqlStatement.getAttributes();
        log.info("columns---{}", attributes);
        log.info("{}", sqlStatement);
    }



}
