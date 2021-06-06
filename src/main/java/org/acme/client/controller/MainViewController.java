package org.acme.client.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.acme.client.model.FxOrder;
import org.acme.server.model.Order;
import org.acme.server.service.IOrderService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Singleton
public class MainViewController implements Initializable {

    public TableView<FxOrder> exampleTable;
    public TableColumn<FxOrder, Integer> orderIdColumn;
    public TableColumn<FxOrder, String> stateColumn;
    public TableColumn<FxOrder, String> cityColumn;

    @Inject
    IOrderService orderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        List<Order> orders = orderService.getOrders();

        exampleTable.setItems(FXCollections.observableList(orders.stream()
                        .map(FxOrder::new)
                        .collect(Collectors.toList())
                )
        );
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
}
