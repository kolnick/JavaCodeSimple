package com.caochaojie.datasource;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: hdd
 * @Date: 2020/6/3 下午1:51
 */
public class UpgradeScript {
    private static String SELECT = "select";
    private static String SHOW = "show";

    private final static Logger logger = LoggerFactory.getLogger(UpgradeScript.class);

    public static void execInitScripts(String path, Connection con) throws IOException, SQLException {
        if (path == null) {
            throw new IllegalArgumentException("路径不能为空");
        }

        logger.info("begin parse upgrade script in directory: {}", path);

        PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
        Resource[] resources = scanner.getResources(path);

        // 匹配文件格式：V+版本号+__+字母数字中划线+.sql
        String match = "^[V]([0-9]+)[.]([0-9]+)[.]([0-9]+)[.]([0-9]+)[_][_][a-zA-Z0-9-]*[.][s][q][l]$";

        List<Resource> sqlFiles = Stream.of(resources).filter(resource -> resource.getFilename() != null)
                .filter(resource -> resource.getFilename().matches(match))
                .sorted((r1, r2) -> {
                    String version1 = r1.getFilename().substring(1, r1.getFilename().indexOf("_"));
                    String version2 = r2.getFilename().substring(1, r2.getFilename().indexOf("_"));
                    String[] v1 = version1.split("\\.");
                    String[] v2 = version2.split("\\.");
                    int compare0 = Integer.parseInt(v1[0]) - Integer.parseInt(v2[0]);
                    int compare1 = Integer.parseInt(v1[1]) - Integer.parseInt(v2[1]);
                    int compare2 = Integer.parseInt(v1[2]) - Integer.parseInt(v2[2]);
                    int compare3 = Integer.parseInt(v1[3]) - Integer.parseInt(v2[3]);
                    if (compare0 != 0) {
                        return compare0;
                    }
                    if (compare1 != 0) {
                        return compare1;
                    }
                    if (compare2 != 0) {
                        return compare2;
                    }
                    return compare3;
                })
                .collect(Collectors.toList());

        for (Resource sqlFile : sqlFiles) {
            execInitScript(sqlFile, con);
        }
    }

    public static void execInitScript(Resource resource, Connection con) throws IOException, SQLException {
        logger.info("executing upgrade script: {}", resource.getFilename());

        List<String> sqlList = getSqlList(resource);
        List<List<String>> workedSqlList = new ArrayList<>();
        List<String> noSelectSql = Lists.newArrayList();
        int selectCount = -1;

        Statement statement = null;
        try {
            statement = con.createStatement();
            for (String sql : sqlList) {
                if (sql.toLowerCase().startsWith(SELECT) || sql.toLowerCase().startsWith(SHOW)) {
                    List<String> sqlGroup = Lists.newArrayList();
                    workedSqlList.add(sqlGroup);
                    sqlGroup.add(sql);
                    selectCount++;
                } else {
                    if (selectCount == -1) {
                        noSelectSql.add(sql);
                    } else {
                        workedSqlList.get(selectCount).add(sql);
                    }
                }
            }

            for (String sql : noSelectSql) {
                statement.execute(sql);
            }

            for (List<String> sqlGroup : workedSqlList) {
                if (sqlGroup.size() <= 1) {
                    continue;
                }
                String selectSql = sqlGroup.get(0);
                if (!statement.executeQuery(selectSql).next()) {
                    logger.info(selectSql + "检查语句无数据，执行迁移!");

                    for (int i = 1; i < sqlGroup.size(); i++) {
                        logger.info("executing sql: {}", sqlGroup.get(i));
                        statement.execute(sqlGroup.get(i));
                    }
                } else {
                    logger.info(selectSql + "检查语句有数据，跳过!");
                }
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }

        logger.info("success execute upgrade script: {}", resource.getFilename());
    }

    private static List<String> getSqlList(Resource resource) throws IOException {
        List<String> sqlList = new ArrayList<>();

        StringBuilder sqlSb = new StringBuilder();
        byte[] buff = new byte[1024];
        int byteRead;

        InputStream inputStream = resource.getInputStream();

        while ((byteRead = inputStream.read(buff)) != -1) {
            sqlSb.append(new String(buff, 0, byteRead, "utf-8"));
        }
        // Windows 下换行是 \r\n, Linux 下是 \n
        String[] sqlArr = sqlSb.toString()
                .split("(;\\s*\\r\\n)|(;\\s*\\n)");
        for (int i = 0; i < sqlArr.length; i++) {
            String sql = sqlArr[i].replaceAll("--.*", "").trim();
            if (!sql.equals("")) {
                sqlList.add(sql);
            }
        }

        return sqlList;
    }
}
