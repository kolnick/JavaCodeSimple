package com.caochaojie;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.PagerUtils;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author caochaojie
 * 2022/12/21
 * @description
 */
@Slf4j

public class SQLGeneratorTest extends TestCase {

    @Test
    public void test() {
        String str = "adda'qeqeq'trtrt";
        if (str.contains("'")) {
            System.out.println(str.indexOf("'"));
        }
        str = str.replaceAll("'", "\\\\'");
        System.out.println(str);
    }

    public void testCount() {
        String sql = "select * from dd";
        String countSql = PagerUtils.count(sql, DbType.oracle);
        log.info(countSql);
    }

    public void testLimitOracle() {
        String sql = "select * from dd";
        String limitSql = PagerUtils.limit(sql, DbType.oracle, 0, 10);
        log.info(limitSql);
    }

    public void testLimitSqlServer() {
        String sql = "select id from dd";
        String limitSql = PagerUtils.limit(sql, DbType.sqlserver, 0, 10);
        log.info(limitSql);
    }
}
