package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 发送同步消息
 *
 * @author caochaojie
 * @date 2020/01/18
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("group1");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.0.252:9876");
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 5; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("test" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.println("发送状态:" + sendResult + ",消息ID" + sendResult.getMsgId() + "队列ID:" + sendResult.getMessageQueue().getQueueId());
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
