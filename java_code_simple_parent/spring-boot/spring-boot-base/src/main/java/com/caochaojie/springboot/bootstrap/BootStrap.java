package com.caochaojie.springboot.bootstrap;

import com.caochaojie.springboot.service.IOCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@Service
public class BootStrap implements ApplicationRunner {

    @Autowired
    private IOCService iocService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        iocService.action();
    }

}
