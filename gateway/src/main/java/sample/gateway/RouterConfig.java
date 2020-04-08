package sample.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author OGAWA, Takeshi
 */
@Configuration
public class RouterConfig {

	@Bean
	RouterFunction<ServerResponse> fallbackRoutes(FallbackHandler handler) {
		return handler.routes();
	}

}
