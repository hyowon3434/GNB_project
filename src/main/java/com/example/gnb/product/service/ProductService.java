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
    public Product createProduct(CreateProductRequest request){
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
                        .product_name(request.getProduct_name())
                        .selling_price(request.getSelling_price())
                        .purchase_price(request.getPurchase_price())
                        .shipping_charge(request.getShipping_charge())
                        .is_vat(request.getIs_vat())
                        .sales_expenses(request.getSales_expenses())
                        .extra_expenses(request.getExtra_expenses())
                        .platform_fee(request.getPlatform_fee())
                        .platform_fee_per(request.getPlatform_fee_per())
                        .is_free_shipping(request.getIs_free_shipping())
                        .vat_invoice(vat_invoice)
                        .tax_service_payment(tax_service_payment)
                        .net_profit((int)net_profit)
                        .margin_Rate(margin_rate)
                        .build();

        Product savedProduct = productRepository.save(product);

        return savedProduct;
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
