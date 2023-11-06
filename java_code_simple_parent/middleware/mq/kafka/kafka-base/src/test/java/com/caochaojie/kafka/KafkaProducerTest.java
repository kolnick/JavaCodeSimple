package com.caochaojie.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caochaojie
 * @Date 2022/8/4
 */
@SpringBootTest(classes = KafkaApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class KafkaProducerTest {

    private int index;
    //注入kafka模板
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void send() {
        String message = "hello";
        kafkaTemplate.send("test", message + index);
    }
}
