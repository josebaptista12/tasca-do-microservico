package com.academy.orderservice.controller;
import com.academy.orderservice.dto.ProductDTO;
import com.academy.orderservice.model.Order;
import com.academy.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repository;
    private final RestTemplate restTemplate;
    @Value("${product.service.url}")
    private String productServiceUrl;
    public OrderController(OrderRepository repository,
                           RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }
    // GET /api/orders — List all orders
    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = repository.findAll();
// Enrich each order with product details
        orders.forEach(this::enrichWithProductData);
        return orders;
    }
    // GET /api/orders/{id} — Get one order
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long id) {
        return repository.findById(id)
                .map(order -> {
                    enrichWithProductData(order);
                    return ResponseEntity.ok(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // POST /api/orders — Create an order
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestBody Order order) {
// Validate product exists by calling Product Service
        try {
            restTemplate.getForObject(
                    productServiceUrl + "/api/products/"
                            + order.getProductId(),
                    ProductDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest()
                    .body("Product not found with ID: "
                            + order.getProductId());

        }
        order.setOrderDate(
                java.time.LocalDateTime.now());
        order.setStatus("CREATED");
        Order saved = repository.save(order);
        enrichWithProductData(saved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }
    // DELETE /api/orders/{id} — Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    // Helper: call Product Service to get product details
    private void enrichWithProductData(Order order) {
        try {
            ProductDTO product = restTemplate.getForObject(
                    productServiceUrl + "/api/products/"
                            + order.getProductId(),
                    ProductDTO.class);
            if (product != null) {
                order.setProductName(product.getName());
                order.setProductPrice(
                        product.getPrice());
            }
        } catch (Exception e) {
            order.setProductName("[unavailable]");
        }
    }
}