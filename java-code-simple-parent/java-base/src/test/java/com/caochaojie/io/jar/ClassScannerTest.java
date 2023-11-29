package com.caochaojie.io.jar;

import io.jar.ScanExecutor;
import org.junit.Test;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author caochaojie
 * @date 2019/09/06
 */
public class ClassScannerTest {
    public static Set<Class<?>> searchClasses(String packageName) {
        return searchClasses(packageName, null);
    }

    public static Set<Class<?>> searchClasses(String packageName, Predicate predicate) {
        return ScanExecutor.getInstance().search(packageName, predicate);
    }

    @Test
    public void test() {
        String packageName = null;// IPlayerActionLog.class.getPackage().getName();
//        packageName += ".modules";
//
//        URL resource = ClassUtil.class.getResource(packageName.replace(".", "/"));
        //先把包名转换为路径,首先得到项目的classpath
        //然后把我们的包名basPack转换为路径名
        //   String basePackPath = packageName.replace(".", File.separator);
        Set<Class<?>> classes = searchClasses(packageName);
        System.out.println(classes);
    }
}
