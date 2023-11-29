package com.caochaojie.script;


import cn.hutool.core.io.resource.ClassPathResource;
import org.testng.annotations.Test;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class TestPython {

    private ScriptEngine engine = null;
    private Invocable invocableEngine = null;                    // 执行引擎
    private Compilable compEngine = null;                        // 编译引擎
    private Map<String, CompiledScript> compiledScripts = null;    // 编译的脚本
    private static String engineName = "jython";                // 脚本引擎
    public static final String SCRIPT_METHOD_NAME = "main"; // 方法
    private static ScriptEngineManager manager = new ScriptEngineManager();

    @Test
    public void test() throws ScriptException, NoSuchMethodException {
        engine = manager.getEngineByName(engineName);
        if (engine instanceof Invocable) {
            invocableEngine = (Invocable) engine;
        }
        if (engine instanceof Compilable) {
            compEngine = (Compilable) engine;
        }
        try {
            ClassPathResource classPathResource = new ClassPathResource("demo.py");
            String path = classPathResource.getPath();
            CompiledScript cs = compEngine.compile(new FileReader(path));
            cs.eval();
            int d = 1;
            long avg = 0;
            int ct = 1;
            String funName = "sum";
            for (int k = 0; k < ct; k++) {
                long s1 = System.currentTimeMillis();
                for (int i = 0; i < d; i++) {
                    Object o = invocableEngine.invokeFunction(funName, 1, 2);
                    System.out.println(o);
                }
                long s2 = System.currentTimeMillis();
                long result = s2 - s1;
                System.out.println("第" + k + "次耗时" + result);
                avg += result;
            }
            System.out.println(ct + "次中平均" + d + "次速度:" + avg / ct);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
