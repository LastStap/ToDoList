package dumshenko.daniil.todolist.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    String schemeKey = "BearerAuth";

    SecurityScheme securityScheme =
        new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);

    Components components = new Components().addSecuritySchemes(schemeKey, securityScheme);
    SecurityRequirement securityRequirement =
            new SecurityRequirement().addList(schemeKey, new ArrayList<>());

    Server server = new Server();
    server.setDescription("ToDo List API");
    server.setUrl("/");

    Info info = new Info()
            .title("ToDoList API")
            .version("1.0.0")
            .description("API documentation for ToDoList application");

    return new OpenAPI()
            .info(info)
            .servers(List.of(server))
            .components(components)
            .addSecurityItem(securityRequirement);
  }
}
