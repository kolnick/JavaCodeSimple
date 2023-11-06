package com.caochaojie.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author caochaojie
 * 2022/12/9
 * @description
 */
@Component
@Slf4j
public class NacosServiceListener {


    public void registerHandler(String discoveryUrl, String serviceName, Consumer register, Consumer deRegister) {

        try {
            NamingService namingService = NacosFactory.createNamingService(discoveryUrl);
            namingService.subscribe(serviceName, event -> {
                if (event instanceof NamingEvent) {
                    List<Instance> instances = ((NamingEvent) event).getInstances();
                    if (checkAnyInstanceEnableHealthy(instances)) {
                        register.accept(serviceName);
                    } else {
                        deRegister.accept(serviceName);
                    }
                }
            });
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检测服务是否可用  *  * @param instances  * @return
     */
    private boolean checkAnyInstanceEnableHealthy(List<Instance> instances) {
        if (CollectionUtils.isEmpty(instances)) {
            return false;
        }
        for (Instance instance : instances) {
            if (instance.isEnabled() && instance.isHealthy()) {
                return true;
            }
        }
        return false;
    }

}

