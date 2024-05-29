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
    public Product createProduct(CreateProductRequest request, Long userId){
        int vat_invoice, tax_service_payment;
        float net_profit;
        float margin_rate;
        int expenses;

        // 세금계산서 발급  = 상품 매입가의 10%
        vat_invoice = (int) (request.getPurchase_price() * 0.1);

        // 국세청 납부
        // 판매가 - 매입가 X 0.1
        tax_service_payment = (int) ((request.getSelling_price() - request.getPurchase_price()) * 0.1);
        expenses = (request.getPurchase_price() + (vat_invoice + tax_service_payment) + request.getSales_expenses() + request.getExtra_expenses() +
                request.getPlatform_fee() + 3000);
        //판매 순이익 = (상품판매가+배송비) - (매입가 + 부가가치세(세금계산서 발급 + 국세청 납부)
        // + 포장, 사은품 비용 + 기타비용 + 플랫폼 수수료 + 무료배송 비용)
        net_profit = (request.getSelling_price() + request.getShipping_charge()) - expenses;

        margin_rate =  (net_profit / request.getSelling_price()) * 100;

        Product product = Product.builder()
                        .productName(request.getProduct_name())
                        .sellingPrice(request.getSelling_price())
                        .purchasePrice(request.getPurchase_price())
                        .shippingCharge(request.getShipping_charge())
                        .isVat(request.getIs_vat())
                        .salesExpenses(request.getSales_expenses())
                        .extraExpenses(request.getExtra_expenses())
                        .platformFee(request.getPlatform_fee())
                        .platformFeePer(request.getPlatform_fee_per())
                        .isFreeShipping(request.getIs_free_shipping())
                        .vatInvoice(vat_invoice)
                        .taxServicePayment(tax_service_payment)
                        .netProfit((int)net_profit)
                        .marginRate(margin_rate)
                        .build();

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    // 전체 상품정보 조회
    public List<Product> selectAllProduct(Long userId){
        List<Product> selectProductResponses = productRepository.findByUserId(userId);
        return selectProductResponses;
    }

    // 선택 상품정보 조회
    public Product selectOneProduct(Long productId, Long userId){
         return productRepository.findByProduct_idAndUserId(productId, userId);
    }

    // 선택된 상품정보 수정
    public List<Product> modifySelectedProduct(Long product_id, UpdateProductRequest request){
        Product product = productRepository.findById(product_id).get();
        product.setProductName(request.getProduct_name());
        product.setSellingPrice(request.getSelling_price());
        product.setShippingCharge(request.getShipping_charge());
        product.setIsVat(request.getIs_vat());
        product.setSalesExpenses(request.getSales_expenses());
        product.setExtraExpenses(request.getExtra_expenses());
        product.setPlatformFee(request.getPlatform_fee());
        product.setPlatformFeePer(request.getPlatform_fee_per());
        product.setIsFreeShipping(request.getIs_free_shipping());

        productRepository.save(product);

        return productRepository.findByUserId(userId);
    }

    // 선택된 상품정보 삭제
    public Product deleteSelectedProduct(Long productId, Long userId){
       return productRepository.deleteProductByProduct_idAndUserId(productId, userId);

    }

    // 전체 상품정보 삭제
    public void deleteAllProduct(Long userId){
        productRepository.deleteProductByUserId(userId);
    }


}
