package com.caochaojie.kafka.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author caochaojie
 * @Date 2022/8/7
 */
@Service
@Slf4j
public class ApplicationUp implements ApplicationRunner {

    @Autowired
    private KafkaAdmin admin;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AdminClient adminClient = AdminClient.create(admin.getConfigurationProperties());
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Collection<TopicListing> topic = listTopicsResult.listings().get();
        log.info("当前kafka 所有的topic{}", topic);

        DescribeClusterResult describeClusterResult = adminClient.describeCluster(new DescribeClusterOptions());
        Collection<Node> nodes = describeClusterResult.nodes().get();
        for (Node node : nodes) {
            log.info("当前集群的节点{}", node.host());
        }
    }
}
