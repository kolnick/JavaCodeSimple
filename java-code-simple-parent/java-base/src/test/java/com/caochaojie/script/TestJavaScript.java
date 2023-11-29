package com.caochaojie.script;


import org.testng.annotations.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestJavaScript {

    @Test
    public void testJavaScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        try {
            String jsFileName = "testWeakRefence.js";   // 读取js文件
            FileReader reader = new FileReader(jsFileName);   // 执行指定脚本
            engine.eval(reader);
            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;    // 调用merge方法，并传入两个参数
                int d = 1000000;
                long avg = 0;
                int ct = 10;
                for (int k = 0; k < ct; k++) {
                    long s1 = System.currentTimeMillis();
                    for (int i = 0; i < d; i++) {
                        Double c = (Double) invoke.invokeFunction("sum", 2, 3);
                    }
                    long s2 = System.currentTimeMillis();
                    long result = s2 - s1;
                    System.out.println("第" + k + "次耗时" + result);
                    avg += result;
                }
                System.out.println(ct + "次中平均" + d + "次速度:" + avg / ct);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
