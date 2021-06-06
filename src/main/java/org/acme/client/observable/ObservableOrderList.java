package org.acme.client.observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.acme.client.observable.dto.ObservableOrder;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class ObservableOrderList<T extends ObservableOrder> {

    private final ObservableList<T> orders;

    public ObservableOrderList() {
        orders = FXCollections.observableArrayList();
    }

    public ObservableList<T> getOrders() {
        return orders;
    }

    public void addOrder(T order) {
        log.info("adding Order#" + order.getId());
        orders.add(order);
    }
}
