package sample.a;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class GreetingHandler {

    RouterFunction<ServerResponse> routes() {
        return route()
                .GET("/hello", this::hello)
                .GET("/bye", this::bye)
                .build();
    }

    Mono<ServerResponse> hello(ServerRequest req) {
        return ok().bodyValue("Hello from Service A");
    }

    Mono<ServerResponse> bye(ServerRequest req) {
        return ok().bodyValue("Good bye from Service A");
    }

}
