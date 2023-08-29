package dev.viyika.recommerceapi.category.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class CategoryRouter {
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route()
                .path("/category", builder -> builder
                        .GET("", this::allCategories)
                        .GET("/{id}", this::categoryById)
                        .POST("", this::newCategory)
                        .PUT("/{}", this::updateCategory)
                        .DELETE("/{id}", this::deleteCategory)
                        .build()
                )
                .build();
    }

    private Mono<ServerResponse> deleteCategory(ServerRequest request) {
        return null;
    }

    private Mono<ServerResponse> updateCategory(ServerRequest request) {
        return null;
    }

    private Mono<ServerResponse> newCategory(ServerRequest request) {
        return null;
    }

    private Mono<ServerResponse> categoryById(ServerRequest request) {
        return null;
    }

    private Mono<ServerResponse> allCategories(ServerRequest request) {
        return null;
    }
}
