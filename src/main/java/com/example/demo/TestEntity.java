package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "test")
public class TestEntity {
    @Id
    private int id;

    public TestEntity() {
    }

    public TestEntity(int id) {
        this.id = id;
    }
}
