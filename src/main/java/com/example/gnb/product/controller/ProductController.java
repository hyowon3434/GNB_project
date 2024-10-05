package com.example.gnb.product.controller;

import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.dto.UpdateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.service.ProductService;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    // 상품정보 등록
    @PostMapping
    public Product createProduct(@RequestBody CreateProductRequest request,
                                 @RequestHeader("Authorization") String authorization){
        return productService.createProduct(request, getCurrentUserEmail(authorization));
    }

    // 전체 상품정보 조회
    @GetMapping
    public List<Product> selectAllProduct(@RequestHeader("Authorization") String authorization){

        return productService.selectAllProduct(getCurrentUserEmail(authorization));
    }

    // 선택된 상품정보 조회
    @GetMapping("/get")
    public Product selectOneProduct(@RequestParam("productId") Long productId,
                                    @RequestHeader("Authorization") String authorization){
        return productService.selectOneProduct(productId, getCurrentUserEmail(authorization));
    }

    // 선택된 상품정보 수정
    @PutMapping
    public List<Product> modifySelectedProduct(@RequestParam("productId") Long productId,
                                               @RequestBody UpdateProductRequest request,
                                               @RequestHeader("Authorization") String authorization){
        return productService.modifySelectedProduct(productId, getCurrentUserEmail(authorization), request);
    }

    // 선택된 상품정보 삭제
    @DeleteMapping("/one")
    public ResponseEntity<?> deleteSelectedProduct(@RequestParam("productId") Long productId,
                                                   @RequestHeader("Authorization") String authorization){
        productService.deleteSelectedProduct(productId, getCurrentUserEmail(authorization));
        return ResponseEntity.ok("성공");

    }

    // 전체 상품정보 삭제
    @DeleteMapping
    public void deleteAllProduct(@RequestHeader("Authorization") String authorization){
        productService.deleteAllProduct(getCurrentUserEmail(authorization));
    }

    private String getCurrentUserEmail(String authorization){
        String token = authorization.substring(6);
        log.warn(token);
        String userEmail = tokenProvider.getUserEmailFromJWT(token);

        User currentUser = userRepository.findByEmail(userEmail);
        if (currentUser == null) {
            throw new NullPointerException("현재 유저 정보가 없습니다");
        }
        return currentUser.getEmail();
    }
}
