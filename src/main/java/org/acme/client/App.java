package org.acme.client;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import javafx.application.Application;
import org.acme.client.conf.FxApplication;

@QuarkusMain
public class App implements QuarkusApplication {

    public static void main(String[] args) {
        Quarkus.run(App.class);
    }

    @Override
    public int run(final String... args) {
        Application.launch(FxApplication.class, args);
        return 0;
    }
}