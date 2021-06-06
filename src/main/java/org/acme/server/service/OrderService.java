package org.acme.server.service;

import org.acme.server.model.Order;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderService implements IOrderService {

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "New York", "New York")); //8,323,340
        orders.add(new Order(2, "Colorado", "Denver"));
        orders.add(new Order(3, "Missouri", "Kansas City"));
        orders.add(new Order(4, "Nebraska", "Custer"));
        orders.add(new Order(5, "Iowa", "Black Hawk"));
        orders.add(new Order(6, "Nevada", "Las Vegas"));
        orders.add(new Order(7, "California", "San Diego")); //1.54m
        orders.add(new Order(8, "Illinois", "Chicago")); //2.2m
        orders.add(new Order(9, "Massachusetts", "Boston")); //4.3m
        orders.add(new Order(10, "Vermont", "Montpellier"));
        orders.add(new Order(11, "Alberta", "Revelstoke"));
        orders.add(new Order(12, "Manitoba", "Winnipeg"));
        orders.add(new Order(13, "British Colombia", "Terrace"));

        return orders;
    }
}
