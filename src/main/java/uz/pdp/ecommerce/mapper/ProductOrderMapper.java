package uz.pdp.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.entity.ProductOrder;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.ProductRepository;
import uz.pdp.ecommerce.request.ProductOrderRequest;
import uz.pdp.ecommerce.response.ProductOrderResponse;

@Component
@RequiredArgsConstructor
public class ProductOrderMapper {

    private final AuthUserRepository authUserRepository;
    private final ProductRepository productRepository;

    public ProductOrder toEntity(ProductOrderRequest productOrderRequest) {
        AuthUser authUser = authUserRepository.findById(productOrderRequest.getAuthUserId())
                .orElseThrow(() -> new ResourceNotFoundException("AuthUser not found: " + productOrderRequest.getAuthUserId()));
        Product product = productRepository.findById(productOrderRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productOrderRequest.getProductId()));
        return ProductOrder.builder()
                .authUser(authUser)
                .product(product)
                .totalPrice(productOrderRequest.getTotalPrice())
                .quantity(productOrderRequest.getQuantity())
                .paymentType(productOrderRequest.getPaymentType())
                .createdAt(productOrderRequest.getCreatedAt())
                .updatedAt(productOrderRequest.getUpdatedAt())
                .build();
    }

    public ProductOrderResponse toResponse(ProductOrder productOrder) {
        return ProductOrderResponse.builder()
                .authUserId(productOrder.getAuthUser().getId())
                .productId(productOrder.getProduct().getId())
                .totalPrice(productOrder.getTotalPrice())
                .quantity(productOrder.getQuantity())
                .paymentType(productOrder.getPaymentType())
                .createdAt(productOrder.getCreatedAt())
                .updatedAt(productOrder.getUpdatedAt())
                .build();
    }
}
