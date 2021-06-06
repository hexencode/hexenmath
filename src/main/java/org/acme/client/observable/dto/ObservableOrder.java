package org.acme.client.observable.dto;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.acme.server.model.Order;

public class ObservableOrder {
    IntegerProperty id;
    StringProperty state;
    StringProperty city;

    public ObservableOrder(Order order) {
        this.id = new SimpleIntegerProperty(order.getId());
        this.state = new SimpleStringProperty(order.getState());
        this.city = new SimpleStringProperty(order.getCity());
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public StringProperty stateProperty() {
        return state;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }
}
