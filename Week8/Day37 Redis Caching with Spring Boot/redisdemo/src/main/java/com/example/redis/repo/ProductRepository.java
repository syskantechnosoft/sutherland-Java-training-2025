package com.example.redis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.redis.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
