package com.academy.productservice.repository;
import com.academy.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {
    // Spring Data generates these automatically:
// findAll(), findById(id), save(product), deleteById(id)
// Custom query methods (auto-generated from name):
    List<Product> findByCategory(String category);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByNameContainingIgnoreCase(String keyword);
}