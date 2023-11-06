package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 发送异步消息
 *
 * @author caochaojie
 * @date 2020/01/18
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("group2");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.0.252:9876");
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 2; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("test2",
                    "TagB",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功:" + sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("发送失败:" + e);
                    e.printStackTrace();
                }
            });
            Thread.sleep(5000);

        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
