package dev.viyika.recommerceapi.category.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Category(long id, String name, Status status, String description) {
}
