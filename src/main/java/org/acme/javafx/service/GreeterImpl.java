package org.acme.javafx.service;

import io.smallrye.mutiny.Uni;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreeterImpl implements Greeter {

  @Override
  public Uni<String> greet(String name) {
    return Uni.createFrom().item(name)
        .onItem().transform(n -> "hello-" + n)
        .onItem().delayIt().by(Duration.ofMillis(100));
  }
}
