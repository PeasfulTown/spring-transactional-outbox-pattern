package xyz.peasfultown.transactional_outbox.search_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConstants {
    public static String exchange;
    public static String queue;
    public static String routingKey;

    public static String exchange_dlq;
    public static String queue_dead;
    public static String routingKey_dead;


    @Value("${rabbitmq.exchange}")
    public void setExchange(String exchange) {
        RabbitMqConstants.exchange = exchange;
    }

    @Value("${rabbitmq.queue}")
    public void setQueue(String queue) {
        RabbitMqConstants.queue = queue;
    }

    @Value("${rabbitmq.routing-key}")
    public void setRoutingKey(String routingKey) {
        RabbitMqConstants.routingKey = routingKey;
    }

    @Value("${rabbitmq.dlq.exchange}")
    public void setExchange_dlq(String exchange_dlq) {
        RabbitMqConstants.exchange_dlq = exchange_dlq;
    }

    @Value("${rabbitmq.dlq.queue")
    public void setQueue_dead(String queue_dead) {
        RabbitMqConstants.queue_dead = queue_dead;
    }

    @Value("${rabbitmq.dlq.routing-key}")
    public void setRoutingKey_dead(String routingKey_dead) {
        RabbitMqConstants.routingKey_dead = routingKey_dead;
    }
}
