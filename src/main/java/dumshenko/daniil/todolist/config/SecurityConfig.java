package dumshenko.daniil.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig {

  private final TokenAuthFilter tokenAuthFilter;

  @Autowired
  public SecurityConfig(TokenAuthFilter tokenAuthFilter) {
    this.tokenAuthFilter = tokenAuthFilter;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      throw new UsernameNotFoundException("No local users, only JWT tokens allowed!");
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .securityContext(
            securityContext ->
                securityContext
                    .requireExplicitSave(true)
                    .securityContextRepository(
                        new DelegatingSecurityContextRepository(
                            new RequestAttributeSecurityContextRepository(),
                            new HttpSessionSecurityContextRepository())))
        .authorizeHttpRequests(
            requests ->
                requests
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/api-docs/**",
                        "/api-docs.yaml",
                        "/webjars/**",
                        "/sign-up",
                        "/login")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
            .cors(
                    cors ->
                            cors.configurationSource(
                                    _ -> {
                                      CorsConfiguration configuration = new CorsConfiguration();
                                      configuration.setAllowedOrigins(List.of("*"));
                                      configuration.setAllowedMethods(List.of("*"));
                                      configuration.setAllowedHeaders(List.of("*"));
                                      return configuration;
                                    }))

            .csrf(AbstractHttpConfigurer::disable)
        .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(
            e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
