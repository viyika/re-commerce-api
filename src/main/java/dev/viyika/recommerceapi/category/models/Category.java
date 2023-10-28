package dev.viyika.recommerceapi.category.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;

@Collation
public record Category(@Id String id, String name, boolean status, String description) {}
