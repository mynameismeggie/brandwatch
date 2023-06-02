package com.brandwatch.shop.ingress.facade.impl;

import com.brandwatch.shop.domain.entity.BaseEntity;
import com.brandwatch.shop.domain.service.OrderService;
import com.brandwatch.shop.egress.producer.OrderMessageProducer;
import com.brandwatch.shop.ingress.facade.OrderFacade;
import com.brandwatch.shop.ingress.mapper.OrderMapper;
import com.brandwatch.shop.ingress.request.CreateOrderRequest;
import com.brandwatch.shop.ingress.request.OrderRequest;
import com.brandwatch.shop.ingress.response.OrderResponse;
import com.brandwatch.shop.ingress.response.ProductOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.brandwatch.shop.domain.enums.OrderStatus.PENDING;
import static com.brandwatch.shop.domain.enums.OrderStatus.SUCCESSFUL;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderMessageProducer orderMessageProducer;

    @Override
    public List<ProductOrderResponse> findAllPendingProducts() {
        final var pendingProducts = orderService.findDistinctProductsByStatus(PENDING);
        return orderMapper.toProductOrderResponse(pendingProducts);
    }

    @Override
    public List<OrderResponse> findAllPendingOrders() {
        final var pendingOrders = orderService.findAllByStatus(PENDING);
        return orderMapper.toOrderResponse(pendingOrders);
    }

    @Override
    public void create(CreateOrderRequest request) {
        final var entityToSave = orderMapper.toEntity(request, PENDING);
        final var entitySaved = orderService.create(entityToSave);
        final var orderRequest = orderMapper.toOrderRequest(entitySaved);
        orderMessageProducer.sendNewOrder(orderRequest);
    }

    @Override
    public void handleSuccessfulOrders(List<OrderRequest> orderRequests) {
        final var successfulOrderIds = orderMapper.toEntity(orderRequests)
                .stream()
                .map(BaseEntity::id)
                .toList();

        orderService.updateStatusByIds(successfulOrderIds, SUCCESSFUL);
    }
}
