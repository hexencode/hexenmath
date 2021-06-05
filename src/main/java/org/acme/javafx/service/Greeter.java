package org.acme.javafx.service;

import io.smallrye.mutiny.Uni;

public interface Greeter {

    Uni<String> greet(String name);

}
