package com.example.gnb.product.service;

import com.example.gnb.product.dto.CreateProductRequest;
import com.example.gnb.product.dto.UpdateProductRequest;
import com.example.gnb.product.entity.Product;
import com.example.gnb.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    // 상품정보 등록
    public Product createProduct(CreateProductRequest request, String userEmail){
        int vat_invoice, tax_service_payment;
        float net_profit;
        float margin_rate;
        int expenses;
        int freeShippingFee = 0;

        if (userEmail == null || userEmail.equals("annonymousUser")) {
            throw new NullPointerException("세션에 유저정보가 없습니다.");
        }

        // 무료배송 체크 되어 있으면 무료배송비 0 -> 3000원
        if (request.getIsFreeShipping() == true){
            freeShippingFee = 3000;
        }

        // 세금계산서 발급  = 상품 매입가의 10%
        if (request.getIsVat() != true) {
            vat_invoice = (int) (request.getPurchasePrice() * 0.1);
        }
        vat_invoice = (int) request.getPurchasePrice();



        // 국세청 납부
        // 판매가 - 매입가 X 0.1
        tax_service_payment = (int) ((request.getSellingPrice() - request.getPurchasePrice()) * 0.1);
        expenses = (request.getPurchasePrice() + (vat_invoice + tax_service_payment) + request.getSalesExpenses() + request.getExtraExpenses() +
                request.getPlatformFee() + freeShippingFee);
        //판매 순이익 = (상품판매가+배송비) - (매입가 + 부가가치세(세금계산서 발급 + 국세청 납부)
        // + 포장, 사은품 비용 + 기타비용 + 플랫폼 수수료 + 무료배송 비용)
        net_profit = (request.getSellingPrice() + request.getShippingCharge()) - expenses;

        margin_rate =  (net_profit / request.getSellingPrice()) * 100;

        Product product = Product.builder()
                        .userEmail(userEmail)
                        .productName(request.getProductName())
                        .sellingPrice(request.getSellingPrice())
                        .purchasePrice(request.getPurchasePrice())
                        .shippingCharge(request.getShippingCharge())
                        .isVat(request.getIsVat())
                        .salesExpenses(request.getSalesExpenses())
                        .extraExpenses(request.getExtraExpenses())
                        .platformFee(request.getPlatformFee())
                        .platformFeePer(request.getPlatformFeePer())
                        .isFreeShipping(request.getIsFreeShipping())
                        .vatInvoice(vat_invoice)
                        .taxServicePayment(tax_service_payment)
                        .netProfit((int)net_profit)
                        .marginRate(margin_rate)
                        .build();

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    // 전체 상품정보 조회
    public List<Product> selectAllProduct(String userEmail){
        if (userEmail == null || userEmail.equals("annonymousUser")) {
            throw new NullPointerException("세션에 유저정보가 없습니다.");
        }
        List<Product> selectProductResponses = productRepository.findByUserEmail(userEmail);
        return selectProductResponses;
    }

    // 선택 상품정보 조회
    public Product selectOneProduct(Long productId, String userEmail){
         return productRepository.findByProductIdAndUserEmail(productId, userEmail);
    }

    // 선택된 상품정보 수정
    public List<Product> modifySelectedProduct(Long product_id, String userEmail, UpdateProductRequest request){
        Product product = productRepository.findById(product_id).get();
        product.setProductName(request.getProductName());
        product.setSellingPrice(request.getSellingPrice());
        product.setShippingCharge(request.getShippingCharge());
        product.setIsVat(request.getIsVat());
        product.setSalesExpenses(request.getSalesExpenses());
        product.setExtraExpenses(request.getExtraExpenses());
        product.setPlatformFee(request.getPlatformFee());
        product.setPlatformFeePer(request.getPlatformFeePer());
        product.setIsFreeShipping(request.getIsFreeShipping());

        productRepository.save(product);

        return productRepository.findByUserEmail(userEmail);
    }

    // 선택된 상품정보 삭제
    public Product deleteSelectedProduct(Long productId, String userEmail){
       return productRepository.deleteByProductIdAndUserEmail(productId, userEmail);

    }

    // 전체 상품정보 삭제
    public void deleteAllProduct(String userEmail){
        productRepository.deleteProductByUserEmail(userEmail);
    }


}
