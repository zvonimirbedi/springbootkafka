package com.springbootkafka.component;

import com.springbootkafka.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void send(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

    /*
    Blocks the sending thread and get the result about the sent message, we can call the get API of the ListenableFuture object.
    The thread will wait for the result, but it will slow down the producer.
    Handle the results asynchronously so that the subsequent messages do not wait for the result of the previous message.
    */
    public void sendMessage(String topic, Message message) {

        ListenableFuture<SendResult<String, Message>> future =
                kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                System.out.println("Sent message: '" + message + "' with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
