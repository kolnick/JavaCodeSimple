package com.caochaojie.springboot.controller;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author caochaojie
 * @Date 2022/8/19
 */
@RestController
@RequestMapping("/")
public class WXController {

    @GetMapping("/")
    public String check(String signature, String timestamp, String nonce, String echostr) {
        System.out.println("signature:" + signature);
        System.out.println("timestamp:" + timestamp);
        System.out.println("nonce:" + nonce);
        System.out.println("echostr:" + echostr);
        boolean equals = checkSignature(signature, timestamp, nonce, echostr);
        System.out.println("equals:" + equals);
        return echostr;
    }

    private boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {

        String token = "ccjkolnick";
        List<String> list = Arrays.asList(token, timestamp, nonce);
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        String checkValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            // 使用sha1进行加密，获取byte数组
            byte[] digest = messageDigest.digest(sb.toString().getBytes());
            StringBuilder sum = new StringBuilder();
            for (byte b : digest) {
                sum.append(Integer.toHexString((b >> 4) & 15));
                sum.append(Integer.toHexString(b & 15));
            }
            checkValue = sum.toString();
            System.out.println(checkValue);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (ObjectUtils.isEmpty(checkValue) || ObjectUtils.isEmpty(signature)) {
            return false;
        }
        return checkValue.contentEquals(signature);
    }
}
