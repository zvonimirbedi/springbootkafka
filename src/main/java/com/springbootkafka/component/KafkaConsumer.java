package com.springbootkafka.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(
            topics = "messagestopic",
            groupId = "groupId"
            // start listening topic from beginning
            /*
            ,topicPartitions = { @TopicPartition(topic = "messagestopic",
                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))
            }
             */
    )
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: '" + message + "' from partition: " + partition);
    }

    @KafkaListener(
            topics = "topicname",
            groupId = "groupId",
            // start listening topic from beginning
            topicPartitions = { @TopicPartition(topic = "topicname",
                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))}
    )
    public void listenWithHeadersTopic2(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: '" + message + "' from partition: " + partition);
    }

}
