package com.syes.syes_springboot.component;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("chat"),
                    exchange = @Exchange(name = "chats", type = "topic"),
                    key = {"to.* "}

            )
    })
    public void ReceiveAll(String message) {
        System.out.println("user消费者message = " + message);
    }
}
