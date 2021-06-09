package org.acme.client.observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;
import org.acme.client.observable.dto.ObservableOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class ObservableOrderList {

    private final ObservableList<ObservableOrder> orders;

    public ObservableOrderList() {
        orders = FXCollections.observableArrayList();
    }

    public ObservableList<ObservableOrder> getOrders() {
        return orders;
    }

    public void addOrder(ObservableOrder order) {
        log.info("adding Order#" + order.getId());
        orders.add(order);
    }
}
