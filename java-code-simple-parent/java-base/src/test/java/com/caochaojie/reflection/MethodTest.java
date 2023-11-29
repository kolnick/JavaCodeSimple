package com.caochaojie.reflection;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @Author: caochaojie
 * @Date: 2023-11-17 21:29
 */
public class MethodTest {
    @Test
    public void checkMethodParam() {
        Method[] declaredMethods = MethodTest.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (!declaredMethod.getName().equals("checkMethodParam")) {
                Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
                if (parameterTypes.length > 2) {
                    System.out.println("error");
                } else {
                    Class<?> paramsArray = parameterTypes[0];
                    boolean ok = paramsArray.isArray() && paramsArray.getComponentType() == Object.class;
                    System.out.println(ok);
                }
            }
        }
    }

    void method(Object[] params) {

    }
}
