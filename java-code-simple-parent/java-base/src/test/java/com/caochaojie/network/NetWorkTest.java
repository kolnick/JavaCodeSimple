package com.caochaojie.network;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caochaojie
 * @Date 2022/8/9
 */
public class NetWorkTest {

    /**
     * 获取网卡信息
     */
    @Test
    public void getNetWorkInfo() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface network = networkInterfaces.nextElement();
                    String displayName = network.getDisplayName();
                    System.out.println("displayName:" + displayName);
                    byte[] hardwareAddress = network.getHardwareAddress();
                    System.out.println("hardwareAddress:" + hardwareAddress);
                    int mtu = network.getMTU();
                    System.out.println("mtu:" + mtu);
                    String name = network.getName();
                    System.out.println("name:" + name);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取本机Mac地
     *
     * @return
     */
    public String getMacAddress() {
        StringBuilder result = null;
        try {
            byte[] mac = NetworkInterface.getByInetAddress(Inet4Address.getLocalHost()).getHardwareAddress();
            result = new StringBuilder();
            // 字节转8进制
            for (int i = 0; null != mac && i < mac.length; i++) {
                if (0 != i) {
                    result.append("-");
                }
                // 转16进制
                String str = Integer.toHexString(mac[i] & 0xff);
                if (1 == str.length()) {
                    result.append("0" + str);
                } else {
                    result.append(str);
                }
            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Test
    public void ipAddress() {
        try {
            InetAddress localHost = Inet4Address.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            System.out.println(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void macAddress() {
        String macAddress = null;
        // macAddress = getMacAddress();
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            try {
                InetAddress localHost = Inet4Address.getLocalHost();
                String hostAddress = localHost.getHostAddress();
                System.out.println(hostAddress);
                macAddress = getMacInWindow(hostAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        System.out.println(macAddress);
    }

    public static String getMacInWindow(String address) {
        String result = callCmd("arp -a");
        String macAddress = filterIpAdress(result, address);
        return macAddress;
    }

    public static String filterIpAdress(String sourceString, String ip) {
        String macSeparator = "-";
        String result = "";
        String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(sourceString);
        int index = sourceString.indexOf(ip);
        if (index != -1) {
            String subString = sourceString.substring(index, sourceString.length());
            String ss = sourceString.substring(0, subString.indexOf("动"));
            if (index != -1) {
                while (matcher.find()) {
                    String macAccress = matcher.group(1);
                    if (ss.indexOf(macAccress) != -1) {
                        result = macAccress;
                        break;
                    }
                }
            }
        }
        return result.toUpperCase();
    }

    public static String callCmd(String cmd) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("cmd /c ");
            sb.append(cmd);
            Process exec = Runtime.getRuntime().exec(sb.toString());
            InputStream inputStream = exec.getInputStream();
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("GBK")));
                if (reader != null) {
                    sb = new StringBuilder();
                    String msg = null;
                    while ((msg = reader.readLine()) != null) {
                        sb.append(msg);
                        sb.append("\n");
                    }
                }
                reader.close();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
