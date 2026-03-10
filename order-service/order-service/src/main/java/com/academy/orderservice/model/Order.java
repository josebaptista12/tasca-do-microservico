package com.academy.orderservice.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private LocalDateTime orderDate;
    @Column(nullable = false)
    private String status;
    // Transient field — not stored in DB
    @Transient
    private String productName;
    @Transient
    private Double productPrice;
    // Default constructor (required by JPA)
    public Order() {}
    // Parameterized constructor
    public Order(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = LocalDateTime.now();
        this.status = "CREATED";
    }
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getProductName() { return productName; }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getProductPrice() { return productPrice; }
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}