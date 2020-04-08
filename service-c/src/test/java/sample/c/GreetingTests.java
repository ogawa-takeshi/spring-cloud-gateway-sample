package sample.c;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest
@AutoConfigureWebTestClient
class GreetingTests {

    @Autowired
    private WebTestClient client;

    @Test
    void helloShouldUnauthorized() {
        this.client
                .get().uri("/hello")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void helloShouldOk() {
        this.client.mutateWith(mockJwt().jwt(builder -> builder.claim("scope", "hello")))
                .get().uri("/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello from Service C");
    }

}
