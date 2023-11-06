package com.caochaojie.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author caochaojie
 * @date 2020/03/04
 */
public class GenCodeApp {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局策略配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        projectPath = StringUtils.join("/", projectPath, "autocode").replace('\\', '/');
        System.out.println(projectPath);
        gc.setOutputDir(projectPath);
        // 是否覆盖已有文件
        gc.setFileOverride(false);
        gc.setAuthor("tool generator");
        // 是否打开输出目录,默认true
        gc.setOpen(false);
        // 是否在xml中添加二级缓存配置,默认false
        gc.setEnableCache(false);
        // 开启 swagger2 模式,默认false
        gc.setSwagger2(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setEntityName("%sModel");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        String url = "jdbc:mysql://localhost:7766/test?useUnicode=true&characterEncoding=UTF8&useSSL=false&autoReconnect=true&allowMultiQueries=true&serverTimezone=UTC";
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin123");
        mpg.setDataSource(dsc);

        // 包名配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tingtong.game.server.login.modular");
        pc.setEntity("model.entity");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        final String path = projectPath;
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 不用手动修改controller了
        templateConfig.setController("/templates/MyController.java");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否为lombok模型
        strategy.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(false);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 定制 controller 的父类
  /*      strategy.setSuperControllerClass("com.tingtong.game.server.login.core.BaseController");
        strategy.setSuperServiceClass("com.tingtong.game.server.login.base.service.BaseService");
        strategy.setSuperServiceImplClass("com.tingtong.game.server.login.base.service.BaseServiceImpl");*/

   /*     // 定制 service 的父类
        // 定制 ServiceImpl 的父类
        // 定制 ServiceImpl 的带缓存的父类
        strategy.setSuperServiceImplClass("com.zy.mybatisplus.base.service.impl.BaseServiceWithRedisImpl");
        // 定制 Mapper 的父类
        strategy.setSuperMapperClass("com.zy.mybatisplus.base.mapper.BaseMapper");
        // 定制 Entity 的父类
        strategy.setSuperEntityClass("com.zy.mybatisplus.base.model.entity.BaseEntity");
*/
        //包含的表名
        String modelName = scanner("表名");
        strategy.setInclude(modelName);
        pc.setModuleName(StringUtils.lowerCase(modelName));
        // 驼峰转连字符 如 umps_user 变为 upms/user
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
