package com.example.gnb.product.controller;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.dto.UpdateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.service.ProductService;
import com.example.gnb.user.entity.User;
import jakarta.servlet.http.HttpSession;
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
    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequest request,
                                 HttpSession session){
        return productService.createProduct(request, getCurrentUserEmail(session));
    }

    // 전체 상품정보 조회
    @GetMapping
    public List<Product> selectAllProduct(HttpSession session){
        return productService.selectAllProduct(getCurrentUserEmail(session));
    }

    // 선택된 상품정보 조회
    @GetMapping("/get")
    public Product selectOneProduct(@RequestParam("productId") Long productId,
                                    HttpSession session){
        return productService.selectOneProduct(productId, getCurrentUserEmail(session));
    }

    // 선택된 상품정보 수정
    @PutMapping
    public List<Product> modifySelectedProduct(@RequestParam("productId") Long productId,
                                               @RequestBody UpdateProductRequest request,
                                               HttpSession session){
        return productService.modifySelectedProduct(productId, getCurrentUserEmail(session), request);
    }

    // 선택된 상품정보 삭제
    @DeleteMapping
    public Product deleteSelectedProduct(@RequestParam("productId") Long productId,
                                         HttpSession session){
        return productService.deleteSelectedProduct(productId, getCurrentUserEmail(session));
    }

    // 전체 상품정보 삭제
    @DeleteMapping("/one")
    public void deleteAllProduct(HttpSession session ){
        productService.deleteAllProduct(getCurrentUserEmail(session));
    }

    private String getCurrentUserEmail(HttpSession session){
        User currentUser = (User)session.getAttribute("user");
        if (currentUser == null) {
            throw new NullPointerException("현재 유저 정보가 없습니다");
        }
        return currentUser.getEmail();
    }
}
