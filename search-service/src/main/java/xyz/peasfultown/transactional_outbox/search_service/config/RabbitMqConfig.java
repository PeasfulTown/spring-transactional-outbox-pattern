package xyz.peasfultown.transactional_outbox.search_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.peasfultown.transactional_outbox.search_service.domain.Ticket;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitMqConstants.exchange);
    }

    @Bean
    public DirectExchange exchange_dead() {
        return new DirectExchange(RabbitMqConstants.exchange_dlq);
    }

    @Bean
    public Queue queue() {
        Map<String, Object> arguments = Map.of(
                "x-dead-letter-exchange", RabbitMqConstants.exchange_dlq,
                "x-dead-letter-routing-key", RabbitMqConstants.routingKey_dead
        );
        return new Queue(RabbitMqConstants.queue, true, false, false, arguments);
    }

    @Bean
    public Queue queue_dead() {
        return new Queue(RabbitMqConstants.queue_dead);
    }

    @Bean
    public Binding binding(TopicExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConstants.routingKey);
    }

    @Bean
    public Binding binding_dead(DirectExchange exchange_dead, Queue queue_dead) {
        return BindingBuilder.bind(queue_dead).to(exchange_dead).with(RabbitMqConstants.routingKey_dead);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonConverter(DefaultClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("Ticket", Ticket.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, TopicExchange exchange, Jackson2JsonMessageConverter jsonConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange(exchange.getName());
        template.setMessageConverter(jsonConverter);
        return template;
    }

}
