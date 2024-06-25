package com.example.movieticketbookingap.Domain;

public class CategoryDomain {
    private int id;
    private String name;

    public CategoryDomain(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDomain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
