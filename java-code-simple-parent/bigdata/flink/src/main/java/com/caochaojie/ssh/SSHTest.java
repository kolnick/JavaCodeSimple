package com.caochaojie.ssh;

/**
 * @author caochaojie
 * @date 2022/6/8
 */
public class SSHTest {

    public static void main(String[] args) {

        //新建会话，此会话用于ssh连接到跳板机（堡垒机），此处为10.1.1.1:22
        // com.jcraft.jsch.Session session = JschUtil.getSession("192.168.40.130", 22, "root", "123");
/*
        // 将堡垒机保护的内网8080端口映射到localhost，我们就可以通过访问http://localhost:8080/访问内网服务了
        JschUtil.bindPort(session, "172.20.12.123", 8080, 8080);
*/

        String cmd = "/opt/flink/flink-1.13.5/bin/flink run /opt/flink/flink-1.13.5/java/flink.jar";
        Shell shell = new Shell("192.168.40.130", "root", "123");
        // cmd = "mkdir -p /opt/ssh/test/ok";
        // cmd = "cd /opt; ls-l;";
        cmd = "source /etc/profile && source ~/.bash_profile && source ~/.bashrc  && "+ cmd;
        String execLog = shell.execCommand(cmd);
        System.out.println(execLog);

    }
}
