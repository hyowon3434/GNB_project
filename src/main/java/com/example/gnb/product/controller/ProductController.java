package com.example.gnb.product.controller;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 상품정보 등록
    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequest request){
        return productService.createProduct(request);
    }

    // 전체 상품정보 조회
    @GetMapping
    public List<Product> selectProduct(){
        return productService.selectProduct();
    }
}
