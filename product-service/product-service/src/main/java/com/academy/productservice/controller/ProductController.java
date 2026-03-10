package com.academy.productservice.controller;
import com.academy.productservice.model.Product;
import com.academy.productservice.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }
    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Product create(@RequestBody Product product) {
        return repository.save(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product product) {

        return repository.findById(id)
                .map(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setCategory(product.getCategory());
                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}