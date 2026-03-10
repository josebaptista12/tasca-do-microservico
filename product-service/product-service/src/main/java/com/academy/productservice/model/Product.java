package com.academy.productservice.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "category")
    private String category;
    // Default constructor (required by JPA)
    public Product() {}
    // Constructor with fields
    public Product(String name, String description,
                   BigDecimal price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
// Getters and setters
public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCategory() { return category; }
    public void setCategory(String category) {
        this.category = category;
    }
}