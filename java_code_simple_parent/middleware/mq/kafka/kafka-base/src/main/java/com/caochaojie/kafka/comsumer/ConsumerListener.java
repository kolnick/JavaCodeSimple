package com.caochaojie.kafka.comsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@Component
@Slf4j
public class ConsumerListener {

    @KafkaListener(topics = "test", groupId = "topic1")
    public void listener(ConsumerRecord<?, ?> record) throws Exception {
        log.info(String.format("topic:%s, offset:%d, value:%s ", record.topic(), record.offset(), record.value()));
        throw new Exception("123");
    }


}
