package org.acme.server.service;

import org.acme.server.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();
}
