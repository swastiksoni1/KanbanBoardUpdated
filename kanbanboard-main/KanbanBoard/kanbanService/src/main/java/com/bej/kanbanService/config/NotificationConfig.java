package com.bej.kanbanService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    public static final String NQUEUE = "user-notification-queue";
    public static final String BQUEUE = "board-notification-queue";
    public static final String EXCHANGE = "board-notification-queue";
    public static final String NRKEY = "user-routing";
    public static final String BRKEY = "board-routing";
    @Bean
    public DirectExchange userNotificationExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue userNotificationQueue() {
        return new Queue(NQUEUE);
    }

    @Bean
    public Queue boardNotificationQueue() {
        return new Queue(BQUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Binding userBinding(DirectExchange userNotificationExchange, Queue userNotificationQueue) {
        return BindingBuilder.bind(userNotificationQueue()).to(userNotificationExchange).with(NRKEY);
    }

    @Bean
    public Binding boardBinding(DirectExchange userNotificationExchange, Queue boardNotificationQueue) {
        return BindingBuilder.bind(boardNotificationQueue()).to(userNotificationExchange).with(BRKEY);
    }
}
