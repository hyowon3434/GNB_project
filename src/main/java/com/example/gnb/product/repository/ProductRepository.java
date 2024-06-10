package com.example.gnb.product.repository;

import com.example.gnb.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId (Long userId);
    Product findByProductIdAndUserId(Long productId, Long userId);
    Product deleteByProductIdAndUserId(Long productId, Long userId);
    Product deleteProductByUserId(Long userId);
}
