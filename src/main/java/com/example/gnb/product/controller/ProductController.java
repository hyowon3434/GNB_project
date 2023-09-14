package com.example.gnb.product.controller;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.dto.UpdateProductRequest;
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
    public List<Product> selectAllProduct(){
        return productService.selectAllProduct();
    }

    // 선택된 상품정보 조회
    @GetMapping("/{product_id}")
    public Product selectOneProduct(@PathVariable("product_id") Long product_id){
        return productService.selectOneProduct(product_id);
    }

    // 선택된 상품정조 수정
    @PutMapping("/{product_id}")
    public List<Product> modifySelectedProduct(@PathVariable("product_id") Long product_id, @RequestBody UpdateProductRequest request){
        return productService.modifySelectedProduct(product_id, request);
    }
}
