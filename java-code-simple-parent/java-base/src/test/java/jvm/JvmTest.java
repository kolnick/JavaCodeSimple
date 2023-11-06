package jvm;

import org.testng.annotations.Test;

/**
 * @author caochaojie
 * @Date 2022/8/9
 */
public class JvmTest {

    @Test
    public void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new MyThread()));
        System.exit(11);
    }
    class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("系统停止");
        }
    }
}
