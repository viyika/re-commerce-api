package dev.viyika.recommerceapi.category;

import dev.viyika.recommerceapi.category.models.Category;
import dev.viyika.recommerceapi.category.repositories.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataMongoTest
public class CategoryRepositoriesTest {

    @Container
    @ServiceConnection
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.3")
            .waitingFor(Wait.forListeningPort());

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("CategoryRepository CRUD Test")
    void crudTest() {
        assertAll(
                //create
                () -> {
                    var category = new Category("1", "test", true, "test");
                    StepVerifier
                            .create(categoryRepository.save(category))
                            .consumeNextWith(savedCategory -> {
                                assertNotNull(savedCategory.id());
                                assertEquals(category.name(), savedCategory.name());
                                assertEquals(category.status(), savedCategory.status());
                                assertEquals(category.description(), savedCategory.description());
                            })
                            .verifyComplete();
                },
                //read
                () -> {
                    //findById
                    StepVerifier
                            .create(categoryRepository.findById("1"))
                            .consumeNextWith(savedCategory -> {
                                assertNotNull(savedCategory.id());
                                assertEquals("test", savedCategory.name());
                                assertTrue(savedCategory.status());
                                assertEquals("test", savedCategory.description());
                            })
                            .verifyComplete();

                    //findAll
                    StepVerifier
                            .create(categoryRepository.findAll())
                            .expectNextCount(1)
                            .verifyComplete();
                },
                //update
                () -> {
                    var category = new Category("1", "test2", false, "test2");
                    StepVerifier
                            .create(categoryRepository.save(category))
                            .consumeNextWith(savedCategory -> {
                                assertNotNull(savedCategory.id());
                                assertEquals(category.name(), savedCategory.name());
                                assertEquals(category.status(), savedCategory.status());
                                assertEquals(category.description(), savedCategory.description());
                            })
                            .verifyComplete();
                },
                //delete
                () -> {
                    StepVerifier
                            .create(categoryRepository.deleteById("1"))
                            .verifyComplete();
                },
                //verify delete
                () -> {
                    StepVerifier
                            .create(categoryRepository.findAll())
                            .expectNextCount(0)
                            .verifyComplete();
                }
        );
    }
}
