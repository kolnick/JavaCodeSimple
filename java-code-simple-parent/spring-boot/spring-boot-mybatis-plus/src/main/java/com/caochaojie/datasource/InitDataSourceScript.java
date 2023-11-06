package com.caochaojie.datasource;


import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author caochaojie
 * @Date 2022/11/21
 */
//@Configuration
public class InitDataSourceScript {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException {
        String path = "classpath:initSql/*.sql";
        try {
            UpgradeScript.execInitScripts(path, dataSource.getConnection());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
