package org.acme.client.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.acme.client.html.HTMLLoader;
import org.acme.client.html.OrderHTMLController;
import org.acme.client.observable.ObservableOrderList;
import org.acme.client.observable.dto.ObservableOrder;
import org.acme.server.service.IOrderService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Singleton
public class OrderController implements Initializable {

    @FXML
    public TableView<ObservableOrder> table;

    @FXML
    public TableColumn<ObservableOrder, Integer> orderIdColumn;

    @FXML
    public TableColumn<ObservableOrder, String> stateColumn;

    @FXML
    public TableColumn<ObservableOrder, String> cityColumn;

    @FXML
    private AnchorPane rightPane;

    @Inject
    IOrderService orderService;

    @Inject
    ObservableOrderList observableOrders;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        table.setItems(observableOrders.getOrders());

        WebView webView = HTMLLoader.load(getClass().getResource("/html/view.html"), new OrderHTMLController(observableOrders));
        rightPane.getChildren().add(webView);
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
