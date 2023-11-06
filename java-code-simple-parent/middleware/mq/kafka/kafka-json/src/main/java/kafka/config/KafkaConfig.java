package kafka.config;

import kafka.bean.Bar;
import kafka.bean.Foo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@Configuration
@Slf4j
public class KafkaConfig {


    @Bean
    public NewTopic foos() {
        return new NewTopic("foos", 1, (short) 1);
    }

    @Bean
    public NewTopic bars() {
        return new NewTopic("bars", 1, (short) 1);
    }

    @Bean
    public RecordMessageConverter converter() {
        JsonMessageConverter converter = new JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("kafka.bean");
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("foo", Foo.class);
        mappings.put("bar", Bar.class);
        typeMapper.setIdClassMapping(mappings);
        converter.setTypeMapper(typeMapper);

        // 获取

        return converter;
    }


    /**
     * kafka 错误处理
     *
     * @param template
     * @return
     */
    @Bean
    public SeekToCurrentErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        return new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
    }


}
