package org.acme.server.service;

import io.smallrye.mutiny.Multi;
import org.acme.server.model.Order;

public interface IOrderService {
    Multi<Order> findOrders();
}
