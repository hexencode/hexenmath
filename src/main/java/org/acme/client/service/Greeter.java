package org.acme.client.service;

import io.smallrye.mutiny.Uni;

public interface Greeter {

    Uni<String> greet(String name);

}
