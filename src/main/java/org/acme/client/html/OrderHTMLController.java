package org.acme.client.html;

import com.dukescript.api.javafx.beans.ActionDataEvent;
import com.dukescript.api.javafx.beans.FXBeanInfo;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.acme.client.observable.ObservableOrderList;
import org.acme.client.observable.dto.ObservableOrder;
import org.acme.server.model.Order;

import javax.inject.Singleton;

@Singleton
public class OrderHTMLController implements FXBeanInfo.Provider {

    final ObjectProperty<String> input = new SimpleObjectProperty<>(this, "input");
    final ObjectProperty<OrderElement> selectedOrder = new SimpleObjectProperty<>(this, "selected");
    final ListProperty<OrderElement> observableOrders = new SimpleListProperty<>(this, "todos", FXCollections.observableArrayList());
    final Property<EventHandler<Event>> add = new SimpleObjectProperty<>(this, "add");
    final Property<EventHandler<ActionDataEvent>> remove = new SimpleObjectProperty<>(this, "remove");

    final FXBeanInfo info = FXBeanInfo.newBuilder(this).
            property(input).
            property(selectedOrder).
            property(observableOrders).
            action(remove).
            action(add).
            build();

    public OrderHTMLController(ObservableOrderList observableOrderList) {
        this.observableOrders.addListener((observable, oldValue, newValue) -> {
        });

        add.setValue((Event e) -> {
            addOrder(input.get());
            observableOrderList.addOrder(new ObservableOrder(new Order(1, input.get(), input.get())));
        });
        remove.setValue((ActionDataEvent event) -> {
                    OrderElement toRemove = event.getSource(OrderElement.class);
                    this.observableOrders.get().remove(toRemove);
                }
        );

        addOrder("Berlin");
    }

    public void addOrder(String order) {
        this.observableOrders.add(new OrderElement(order));
    }

    @Override
    public FXBeanInfo getFXBeanInfo() {
        return info;
    }

    private static final class OrderElement implements FXBeanInfo.Provider {
        final String message;
        final FXBeanInfo info;

        OrderElement(String message) {
            this.message = message;
            this.info = FXBeanInfo.newBuilder(this).constant("message", message).build();
        }

        @Override
        public FXBeanInfo getFXBeanInfo() {
            return info;
        }
    }
}