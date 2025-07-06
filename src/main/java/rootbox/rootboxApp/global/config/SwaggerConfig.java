package rootbox.rootboxApp.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI SpringCodeBaseAPI() {
        Info info = new Info()
                .title("RouteBox API")
                .description("RouteBox API 명세서")
                .version("1.0.0");

        final String ACCESS_SCHEME_NAME = "Access Token";
        final String REFRESH_SCHEME_NAME = "Refresh Token";

        Components components = new Components()
                .addSecuritySchemes(ACCESS_SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name("Authorization")
                                .in(SecurityScheme.In.HEADER))
                .addSecuritySchemes(REFRESH_SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name("Refresh") // 예: 헤더 키를 다르게 설정할 수 있음
                                .in(SecurityScheme.In.HEADER));

        return new OpenAPI()
                .info(info)
                .components(components)
                .addServersItem(new Server().url("/"))
                // 여기 두 개 모두 SecurityRequirement에 등록
                .addSecurityItem(new SecurityRequirement().addList(ACCESS_SCHEME_NAME))
                .addSecurityItem(new SecurityRequirement().addList(REFRESH_SCHEME_NAME));
    }
}