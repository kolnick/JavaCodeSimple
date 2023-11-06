package com.caochaojie.generate.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;

public class MybatisGeneratorUtil {
    /**
     * mybatis的mapper自动生成工具
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        mybatisGenerate();
    }

    public static void mybatisGenerate() throws Exception {
        ArrayList<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream resourceAsStream = MybatisGeneratorUtil.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
        System.out.println(resourceAsStream.available());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
