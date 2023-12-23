package com.chaitanyaallu.productcatalog.models;

import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue (generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    //@UuidGenerator
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "binary(16)")
    private UUID uuid;
}