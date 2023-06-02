package com.brandwatch.shop.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String NEW_ORDER_QUEUE = "new-order-queue";
    public static final String SUCCESSFUL_ORDERS_QUEUE = "successful-orders-queue";
    public static final String PENDING_PRODUCTS_QUEUE = "pending-products-queue";
    public static final String PENDING_ORDERS_QUEUE = "pending-orders-queue";

    @Bean
    public Queue newOrderQueue() {
        return new Queue(NEW_ORDER_QUEUE, true);
    }

    @Bean
    public Queue successfulOrdersQueue() {
        return new Queue(SUCCESSFUL_ORDERS_QUEUE, true);
    }

    @Bean
    public Queue pendingProductsQueue() {
        return new Queue(PENDING_PRODUCTS_QUEUE, true);
    }

    @Bean
    public Queue pendingOrdersQueue() {
        return new Queue(PENDING_ORDERS_QUEUE, true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
