package dev.viyika.recommerceapi.category.repositories;

import dev.viyika.recommerceapi.category.models.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, Long> {}
