package com.caochaojie.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.junit.Test;

public class CaptchaTest {

    @Test
    public void test() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        System.out.println(lineCaptcha.getCode());
    }
}
