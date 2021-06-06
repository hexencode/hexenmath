package org.acme.client.conf;

import javafx.application.Application;
import javafx.stage.Stage;
import org.acme.client.event.FxApplicationStarted;

import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;

public class FxApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        CDI.current().getBeanManager().fireEvent(primaryStage, new AnnotationLiteral<FxApplicationStarted>() {
        });
    }

}
