package org.acme.client.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.acme.client.observable.ObservableOrderList;
import org.acme.client.observable.dto.ObservableOrder;
import org.acme.server.service.IOrderService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Singleton
public class MainViewController implements Initializable {

    public TableView<ObservableOrder> table;
    public TableColumn<ObservableOrder, Integer> orderIdColumn;
    public TableColumn<ObservableOrder, String> stateColumn;
    public TableColumn<ObservableOrder, String> cityColumn;

    @Inject
    IOrderService orderService;

    @Inject
    ObservableOrderList<ObservableOrder> observableOrders;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        table.setItems(observableOrders.getOrders());
    }

    @FXML
    public void handleExitButtonClicked(ActionEvent event) {
        Platform.exit();
        event.consume();
    }

    @FXML
    public void handleGitButtonClicked(ActionEvent event) {
        new Application() {
            @Override
            public void start(Stage stage) {
            }
        }.getHostServices().showDocument("https://github.com/hexencode/hexenmath");
        event.consume();
    }

    @FXML
    public void handleSearchButtonClicked(ActionEvent event) {
        log.info("Searching for orders started");
        orderService.findOrders()
                .subscribe()
                .with(order -> observableOrders.addOrder(new ObservableOrder(order)),
                        failure -> log.error("TODO: Handle Error " + failure),
                        () -> log.info("Searching for orders completed"));

        event.consume();
    }
}
