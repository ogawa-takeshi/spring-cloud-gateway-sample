package sample.c;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

	@Bean
	RouterFunction<ServerResponse> helloRoutes(GreetingHandler handler) {
		return handler.routes();
	}

}
