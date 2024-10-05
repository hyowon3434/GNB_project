package com.example.gnb.product.repository;

import com.example.gnb.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserEmail (String userEmail);
    Product findByProductIdAndUserEmail(Long productId, String userEmail);
    void deleteByProductIdAndUserEmail(Long productId, String userEmail);
    Product deleteProductByUserEmail(String userEmail);
}
