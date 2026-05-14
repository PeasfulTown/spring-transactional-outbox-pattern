package xyz.peasfultown.transactional_outbox.search_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConstants {
    public static String exchange;
    public static String queue;
    public static String routingKey;

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
}
