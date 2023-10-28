package dev.viyika.recommerceapi.category;

import dev.viyika.recommerceapi.category.repositories.CategoryRepository;
import dev.viyika.recommerceapi.category.routers.CategoryRouter;
import dev.viyika.recommerceapi.category.services.CategoryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureWebTestClient
@Testcontainers
public class CategoryRouterTest {
    @Container
    @ServiceConnection
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.3")
            .waitingFor(Wait.forListeningPort());

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("/categoty POST")
    @Disabled
    void createTest() {
        webTestClient
                .post()
                .uri("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                """)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("""
                """);
    }

    @Test
    @DisplayName("/categoty GET")
    void readTest() {

    }

    @Test
    @DisplayName("/categoty PUT")
    void updateTest() {

    }

    @Test
    @DisplayName("/categoty DELETE")
    void deleteTest() {

    }



}
