package sample.gateway;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .matchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .pathMatchers("/a/**").permitAll()
                .pathMatchers("/b/**").hasAnyAuthority("SCOPE_hello", "SCOPE_bye")
                .anyExchange().permitAll();
        http.oauth2Login();
        http.oauth2Client();
        http.logout();
        http.cors();
        return http.build();
    }

}
