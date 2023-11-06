package comsumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;

/**
 * @author caochaojie
 * @date 2020/01/18
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {

        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");

        // Specify name server addresses.
        consumer.setNamesrvAddr("192.168.0.252:9876");

        // Subscribe one more more topics to consume.
        // 订阅主题和Tag
        consumer.subscribe("test", "*");
        // 启动负载均衡模式
        // RocketMQ 默认是广播模式
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.println(msgs);
            MessageExt messageExt = msgs.get(0);
            String bornHostString = messageExt.getBornHostString();
            byte[] body = messageExt.getBody();
            try {
                System.out.println(new String(body, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println(bornHostString);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //Launch the consumer instance.
        consumer.start();
        System.out.printf("comsumer.Consumer Started.%n");
    }
}
