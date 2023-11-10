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


    /**
     * jvm基础性信息
     */

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

    /**
     * 内存使用情况
     */
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

    /**
     * 线程管理系统
     */

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

    /**
     * 操作系统信息
     */

    @Test
    public void osInfo() {
        OperatingSystemMXBean osMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        System.out.println("Operating System: " + osMXBean.getName());
        System.out.println("Operating System Version: " + osMXBean.getVersion());
        System.out.println("Operating System Architecture: " + osMXBean.getArch());
        System.out.println("System Load Average: " + osMXBean.getSystemLoadAverage());
    }

    /***
     * 获取类加载管理信息：
     */
    @Test
    public void classLoad() {
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();

        System.out.println("Total Loaded Class Count: " + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println("Loaded Class Count: " + classLoadingMXBean.getLoadedClassCount());
        System.out.println("Unloaded Class Count: " + classLoadingMXBean.getUnloadedClassCount());
        System.out.println("Is Verbose Class Loading Enabled: " + classLoadingMXBean.isVerbose());
    }

    /**
     * 获取垃圾回收管理信息
     */
    @Test
    public void gc() {
        for (GarbageCollectorMXBean gcMXBean : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println("Garbage Collector Name: " + gcMXBean.getName());
            System.out.println("Garbage Collector Collection Count: " + gcMXBean.getCollectionCount());
            System.out.println("Garbage Collector Collection Time: " + gcMXBean.getCollectionTime());
        }
    }

}
