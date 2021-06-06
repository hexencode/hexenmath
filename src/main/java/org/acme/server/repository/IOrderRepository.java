package org.acme.server.repository;

import io.smallrye.mutiny.Multi;
import org.acme.server.model.Order;

public abstract interface IOrderRepository {
    Multi<Order> findOrders();
}
