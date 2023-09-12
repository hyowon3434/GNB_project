package com.example.gnb.product.controller;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 상품정보 등록
    public Product createProduct(@RequestBody CreateProductRequest request){
        return productService.createProduct(request);
    }
}
