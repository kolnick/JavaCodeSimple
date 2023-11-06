package com.caochaojie.springboot.service.impl;

import com.caochaojie.springboot.service.IOCService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@Slf4j
@Service
public class IOCServiceImpl implements IOCService {
    @Override
    public void action() {
        log.info("action");
    }

    @Override
    public String getMessage() {
        return "hello world";
    }
}
