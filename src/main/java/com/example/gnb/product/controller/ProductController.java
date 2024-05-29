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
@CrossOrigin(value = "*")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    // 상품정보 등록
    @PostMapping("/{userId}")
    public Product createProduct(@RequestBody CreateProductRequest request,
                                 @PathVariable("userId") Long userId){
        return productService.createProduct(request, userId);
    }

    // 전체 상품정보 조회
    @GetMapping("/{userId}")
    public List<Product> selectAllProduct(@PathVariable("userId") Long userId){
        return productService.selectAllProduct(userId);
    }

    // 선택된 상품정보 조회
    @GetMapping("/{productId}/{userId}")
    public Product selectOneProduct(@PathVariable("productId") Long productId,
                                    @PathVariable("userId") Long userId){
        return productService.selectOneProduct(productId, userId);
    }

    // 선택된 상품정보 수정
    @PutMapping("/{productId}/{userId}")
    public List<Product> modifySelectedProduct(@PathVariable("productId") Long productId,
                                               @PathVariable("userId") Long userId,
                                               @RequestBody UpdateProductRequest request){
        return productService.modifySelectedProduct(productId, userId, request);
    }

    // 선택된 상품정보 삭제
    @DeleteMapping("/{productId}/{userId}")
    public Product deleteSelectedProduct(@PathVariable("productId") Long productId,
                                         @PathVariable("userId") Long userId){
        return productService.deleteSelectedProduct(productId, userId);
    }

    // 전체 상품정보 삭제
    @DeleteMapping("/{userId}")
    public void deleteAllProduct(@PathVariable("userId") Long userId){
        productService.deleteAllProduct(userId);
    }
}
