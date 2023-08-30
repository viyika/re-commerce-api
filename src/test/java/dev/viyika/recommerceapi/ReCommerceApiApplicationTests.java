package dev.viyika.recommerceapi;

import dev.viyika.recommerceapi.category.models.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReCommerceApiApplicationTests {

	@Container
	@ServiceConnection
	static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.3")
			.waitingFor(Wait.forListeningPort());

	@Autowired
	WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("POST /category")
	@Order(1)
	void test01() {
		webTestClient
				.post()
				.uri("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("""
						{
							"id": "1",
							"name": "test",
							"status": "ACTIVE",
							"description": "test"
						}
						""")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json("""
						{
							"id": "1",
							"name": "test",
							"status": "ACTIVE",
							"description": "test"
						}
						""");
	}

	@Test
	@DisplayName("GET /category/1")
	@Order(2)
	void test02() {
		//find all
		webTestClient
				.get()
				.uri("/category")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(Category.class)
				.hasSize(1);
		//find by id
		webTestClient
				.get()
				.uri("/category/{id}",1)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.json("""
						{
							"id": "1",
							"name": "test",
							"status": "ACTIVE",
							"description": "test"
						}
						""");
	}

	@Test
	@DisplayName("PUT /category/1")
	@Order(3)
	void test03() {
		webTestClient
				.put()
				.uri("/category/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue("""
						{
							"id": "1",
							"name": "test2",
							"status": "ACTIVE",
							"description": "test2"
						}
						""")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json("""
						{
							"id": "1",
							"name": "test2",
							"status": "ACTIVE",
							"description": "test2"
						}
						""");
	}

	@Test
	@DisplayName("DELETE /category/1")
	@Order(4)
	void test04() {
		webTestClient
				.delete()
				.uri("/category/{id}",1)
				.exchange()
				.expectStatus().isOk();
	}

}
