package com.example.gnb.product.service;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.dto.UpdateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 상품정보 등록
    public Product createProduct(CreateProductRequest request){

        Product product = Product.builder()
                        .product_name(request.getProduct_name())
                        .selling_price(request.getSelling_price())
                        .shipping_charge(request.getShipping_charge())
                        .is_vat(request.getIs_vat())
                        .sales_expenses(request.getSales_expenses())
                        .extra_expenses(request.getExtra_expenses())
                        .platform_fee(request.getPlatform_fee())
                        .platform_fee_per(request.getPlatform_fee_per())
                        .is_free_shipping(request.getIs_free_shipping())
                        .build();

        return productRepository.save(product);
    }

    // 전체 상품정보 조회
    public List<Product> selectAllProduct(){
        List<Product> selectProductResponses = productRepository.findAll();
        return selectProductResponses;
    }

    // 선택 상품정보 조회
    public Product selectOneProduct(Long product_id){
         return productRepository.findById(product_id).get();
    }

    // 선택된 상품정보 수정
    public List<Product> modifySelectedProduct(Long product_id, UpdateProductRequest request){
        Product product = productRepository.findById(product_id).get();
        product.setProduct_name(request.getProduct_name());
        product.setSelling_price(request.getSelling_price());
        product.setShipping_charge(request.getShipping_charge());
        product.setIs_vat(request.getIs_vat());
        product.setSales_expenses(request.getSales_expenses());
        product.setExtra_expenses(request.getExtra_expenses());
        product.setPlatform_fee(request.getPlatform_fee());
        product.setPlatform_fee_per(request.getPlatform_fee_per());
        product.setIs_free_shipping(request.getIs_free_shipping());

        productRepository.save(product);

        return productRepository.findAll();
    }

    // 선택된 상품정보 삭제
    public Product deleteSelectedProduct(Long product_id){
        Product product = productRepository.findById(product_id).get();
        productRepository.deleteById(product_id);
        return product;
    }

    // 전체 상품정보 삭제
    public void deleteAllProduct(){
        productRepository.deleteAll();
    }


}
