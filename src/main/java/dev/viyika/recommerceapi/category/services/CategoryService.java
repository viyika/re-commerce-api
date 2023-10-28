package dev.viyika.recommerceapi.category.services;

import dev.viyika.recommerceapi.category.CategoryNotFound;
import dev.viyika.recommerceapi.category.models.Category;
import dev.viyika.recommerceapi.category.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    public Flux<Category> fetchAll() {
        return categoryRepository.findAll();
    }

    public Mono<Category> findById(String id) {
        return categoryRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new CategoryNotFound("Category not found")));
    }

    public Mono<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    public Mono<Category> update(String id, Category category) {
        return categoryRepository
                .findById(id)
                .flatMap(dbCategory -> {
                    var toBeSave = new Category(dbCategory.id(), category.name(), category.status(), category.description());
                    return categoryRepository.save(toBeSave);
                });
    }
    public Mono<Void> deleteById(String id) {
        return categoryRepository.deleteById(id);
    }
}
