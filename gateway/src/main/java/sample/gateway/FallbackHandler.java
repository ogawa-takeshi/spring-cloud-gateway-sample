package sample.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author OGAWA, Takeshi
 */
@Component
public class FallbackHandler {

    @Value("${fallback.message:Service unavailable}")
    private String message;

    RouterFunction<ServerResponse> routes() {
        return route().GET("/fallback", this::fallback).build();
    }

    Mono<ServerResponse> fallback(ServerRequest req) {
        return ok().bodyValue(this.message);
    }

}
