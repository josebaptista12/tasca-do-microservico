package com.academy.orderservice.dto;
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    // Default constructor
    public ProductDTO() {}
    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(Double price) { this.price = price; }
}