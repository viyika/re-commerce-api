package dev.viyika.recommerceapi.category.routers;

import dev.viyika.recommerceapi.category.models.Category;
import dev.viyika.recommerceapi.category.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class CategoryRouter {
    final CategoryService categoryService;
    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions
                .route()
                .path("/category", builder -> builder
                        .GET("", this::allCategories)
                        .GET("/{id}", this::categoryById)
                        .POST("", this::newCategory)
                        .PUT("/{id}", this::updateCategory)
                        .DELETE("/{id}", this::deleteCategory)
                        .build()
                )
                .build();
    }

    private Mono<ServerResponse> deleteCategory(ServerRequest request) {
        var id = request.pathVariable("id");
        return categoryService
                .deleteById(id)
                .then(ServerResponse.ok().build());
    }

    private Mono<ServerResponse> updateCategory(ServerRequest request) {
        var id = request.pathVariable("id");
        return request
                .bodyToMono(Category.class)
                .flatMap(category -> categoryService.update(id, category))
                .flatMap(category -> ServerResponse.ok().bodyValue(category))
                .log();
    }

    private Mono<ServerResponse> newCategory(ServerRequest request) {
        return request
                .bodyToMono(Category.class)
                .flatMap(category -> categoryService.save(category))
                .flatMap(category -> ServerResponse.ok().bodyValue(category))
                .log();
    }

    private Mono<ServerResponse> categoryById(ServerRequest request) {
        var id = request.pathVariable("id");
        return categoryService
                .findById(id)
                .flatMap(category -> ServerResponse.ok().bodyValue(category));
    }

    private Mono<ServerResponse> allCategories(ServerRequest request) {
        return categoryService
                .fetchAll()
                .collectList()
                .flatMap(categories -> ServerResponse.ok().bodyValue(categories));
    }
}
