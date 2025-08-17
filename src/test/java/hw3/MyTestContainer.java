package hw3;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MyTestContainer {

    @Container
    public static GenericContainer<?> myContainer = new GenericContainer<>("alpine:3.2")
            .withExposedPorts(8080)
            .withCommand("/bin/sh", "-c", "while true; do echo \"HTTP/1.1 200 OK\\n\\nHello World!\" | nc -l -p 8080; done");
}
