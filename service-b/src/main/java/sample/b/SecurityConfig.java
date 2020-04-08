package sample.b;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange() //
				.matchers(EndpointRequest.toAnyEndpoint()).permitAll() //
				.pathMatchers("/hello").hasAuthority("SCOPE_hello")
				.pathMatchers("/bye").hasAuthority("SCOPE_bye")
				.anyExchange().authenticated();
		http.oauth2ResourceServer()
				.jwt();
		http.csrf().disable();
		return http.build();
	}

}
