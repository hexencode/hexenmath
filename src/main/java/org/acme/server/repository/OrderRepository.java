package org.acme.server.repository;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.acme.server.model.Order;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@ApplicationScoped
public class OrderRepository implements IOrderRepository {

    private static final Random RANDOM = new Random();
    private static final List<Order> ORDERS = new ArrayList<>();

    static {
        ORDERS.add(new Order(1, "New York", "New York")); //8,323,340
        ORDERS.add(new Order(2, "Colorado", "Denver"));
        ORDERS.add(new Order(3, "Missouri", "Kansas City"));
        ORDERS.add(new Order(4, "Nebraska", "Custer"));
        ORDERS.add(new Order(5, "Iowa", "Black Hawk"));
        ORDERS.add(new Order(6, "Nevada", "Las Vegas"));
        ORDERS.add(new Order(7, "California", "San Diego")); //1.54m
        ORDERS.add(new Order(8, "Illinois", "Chicago")); //2.2m
        ORDERS.add(new Order(9, "Massachusetts", "Boston")); //4.3m
        ORDERS.add(new Order(10, "Vermont", "Montpellier"));
        ORDERS.add(new Order(11, "Alberta", "Revelstoke"));
        ORDERS.add(new Order(12, "Manitoba", "Winnipeg"));
        ORDERS.add(new Order(13, "British Colombia", "Terrace"));
    }

    @Override
    public Multi<Order> findOrders() {
        return Multi.createFrom().items(ORDERS.stream())
                .onItem().call(delay());
    }

    /**
     * simulates delay. The returned nullItem is ignored.
     * @return
     */
    private Function<Order, Uni<?>> delay() {
        return i -> {
            Duration delay = Duration.ofMillis(RANDOM.nextInt(1000) + 10);
            return Uni.createFrom().nullItem().onItem().delayIt().by(delay);
        };
    }
}
