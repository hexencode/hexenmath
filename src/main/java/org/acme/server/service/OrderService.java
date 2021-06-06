package org.acme.server.service;

import io.smallrye.mutiny.Multi;
import org.acme.server.model.Order;
import org.acme.server.repository.IOrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrderService implements IOrderService {

    @Inject
    IOrderRepository orderRepository;

    @Override
    public Multi<Order> findOrders() {
        return orderRepository.findOrders();
    }
}
