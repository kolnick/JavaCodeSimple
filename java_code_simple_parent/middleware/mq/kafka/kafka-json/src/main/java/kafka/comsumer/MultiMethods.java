/*
 * Copyright 2018-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kafka.comsumer;

import kafka.bean.Bar;
import kafka.bean.Foo;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Gary Russell
 * @since 5.1
 */
@Component
@KafkaListener(id = "multiGroup", topics = {"foos", "bars"})
public class MultiMethods {

    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();

    @KafkaHandler
    public void foo(Foo foo) {
        System.out.println("Received: " + foo);
        terminateMessage();
    }

    @KafkaHandler
    public void bar(Bar bar) {
        System.out.println("Received: " + bar);
        terminateMessage();
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Received unknown: " + object);
        terminateMessage();
    }

    private void terminateMessage() {
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

}
