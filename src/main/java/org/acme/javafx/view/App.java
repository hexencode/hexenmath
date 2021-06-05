package org.acme.javafx.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import org.acme.javafx.conf.StartupScene;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.net.URL;

public class App {

    @Inject
    FXMLLoader fxmlLoader;

    private double xOffset;
    private double yOffset;

    @SneakyThrows
    public void start(@Observes @StartupScene Stage primaryStage) {
        URL fxml = getClass().getResource("/fxml/MainView.fxml");
        Parent root = fxmlLoader.load(fxml.openStream());

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            event.consume();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
            event.consume();
        });

        Scene scene = new Scene(root);
        scene.getRoot().setEffect(new DropShadow(10, Color.rgb(100, 100, 100)));
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/EdenCodingIcon.png")));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
