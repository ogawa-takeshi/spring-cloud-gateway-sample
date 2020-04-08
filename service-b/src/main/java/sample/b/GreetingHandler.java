package sample.b;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class GreetingHandler {

    private final WebClient webClient;

    private final String c;

    GreetingHandler(WebClient webClient, @Value("service.c") String c) {
        this.webClient = webClient;
        this.c = c;
    }

    RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/hello", this::hello)
                .GET("/bye", this::bye)
                .build();
    }

    Mono<ServerResponse> hello(ServerRequest req) {
        return this.webClient.get().uri(this.c + "/hello")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(message -> {
                    return ok().bodyValue("Hello from Service B, " + message);
                });
    }

    Mono<ServerResponse> bye(ServerRequest req) {
        return this.webClient.get().uri(this.c + "/bye")
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(message -> {
                    return ok().bodyValue("Good bye from Service B, " + message);
                });
    }

}
