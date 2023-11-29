package com.caochaojie.database;

import cn.hutool.db.Db;
import org.testng.annotations.Test;

/**
 * @Author: caochaojie
 * @Date: 2023-11-22 13:28
 */
public class TableGenTest {

    @Test
    public void test() {
        // 创建数据源
        // 设置数据库连接信息
        TableDefinition td = BeanUtil.tableDefinition(User.class);
        DdlBuilder
    }
}
