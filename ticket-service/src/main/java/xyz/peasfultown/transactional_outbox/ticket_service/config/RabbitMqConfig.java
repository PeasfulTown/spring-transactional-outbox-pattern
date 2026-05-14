package xyz.peasfultown.transactional_outbox.ticket_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitMqConstants.exchange);
    }

    @Bean
    public Queue queue() {
        return new Queue(RabbitMqConstants.queue);
    }

    @Bean
    public Binding binding(TopicExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConstants.routingKey);
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
//        idClassMapping.put("CartCheckoutRequest", CartCheckoutRequest.class);
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
