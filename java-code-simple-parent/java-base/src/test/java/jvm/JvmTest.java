package jvm;

import org.testng.annotations.Test;

import java.lang.management.*;

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


    @Test
    public void jvmInfo() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("JVM Name: " + runtimeMXBean.getVmName());
        System.out.println("JVM Version: " + runtimeMXBean.getVmVersion());
        System.out.println("JVM Vendor: " + runtimeMXBean.getVmVendor());
        System.out.println("JVM Start Time: " + runtimeMXBean.getStartTime());
        System.out.println("JVM Uptime: " + runtimeMXBean.getUptime());
        System.out.println("JVM Input Arguments: " + runtimeMXBean.getInputArguments());
    }

    @Test
    public void memory() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        System.out.println("Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memoryMXBean.getNonHeapMemoryUsage());
        for (MemoryPoolMXBean poolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println("Memory Pool: " + poolMXBean.getName());
            System.out.println("Usage: " + poolMXBean.getUsage());
        }
    }

    @Test
    public void threadMXBean() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        System.out.println("Thread Count: " + threadMXBean.getThreadCount());
        System.out.println("All Thread IDs: " + threadMXBean.getAllThreadIds());

        long[] threadIds = threadMXBean.getAllThreadIds();
        for (long threadId : threadIds) {
            System.out.println("Thread Name: " + threadMXBean.getThreadInfo(threadId).getThreadName());
        }
    }

    @Test
    public void osInfo() {
        OperatingSystemMXBean osMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        System.out.println("Operating System: " + osMXBean.getName());
        System.out.println("Operating System Version: " + osMXBean.getVersion());
        System.out.println("Operating System Architecture: " + osMXBean.getArch());
        System.out.println("System Load Average: " + osMXBean.getSystemLoadAverage());
    }
}
