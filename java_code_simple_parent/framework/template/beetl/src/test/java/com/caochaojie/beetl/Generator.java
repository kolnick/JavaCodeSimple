package com.caochaojie.beetl;

import com.caochaojie.beetl.Field;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author caochaojie
 * 2023/1/10
 * @description
 */
public class Generator {

    @Test
    public void test() {
        //初始化代码
        try {
            ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader("");
            Configuration configuration = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(classpathResourceLoader, configuration);
            Template t = gt.getTemplate("/Model.btl");
            t.binding("entityName", "CfgHero");
            Field build1 = Field.builder().name("a1").type("String").comment("a1_commect").build();
            Field build2 = Field.builder().name("a2").type("long").comment("a2_commect").build();
            List<Field> lists = Arrays.asList(build1, build2);
            t.binding("list", lists);
            t.binding("field", new Object());
            String str = t.render();
            System.out.println(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取模板
    }
}
