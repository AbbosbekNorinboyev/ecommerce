package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.entity.ProductOrder;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.mapper.ProductOrderMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.ProductOrderRepository;
import uz.pdp.ecommerce.repository.ProductRepository;
import uz.pdp.ecommerce.request.ProductOrderRequest;
import uz.pdp.ecommerce.response.ProductOrderResponse;
import uz.pdp.ecommerce.response.ProductResponse;
import uz.pdp.ecommerce.config.SessionId;
import uz.pdp.ecommerce.service.ProductOrderService;
import uz.pdp.ecommerce.validation.ProductOrderValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    private final SessionId sessionId;
    private final ProductRepository productRepository;
    private final ProductOrderMapper productOrderMapper;
    private final AuthUserRepository authUserRepository;
    private final ProductOrderValidation productOrderValidation;
    private final ProductOrderRepository productOrderRepository;

    @Override
    public ResponseDto<ProductOrderResponse> createProductOrder(ProductOrderRequest productOrderRequest) {
        return authUserRepository.findById(sessionId.getSessionId())
                .flatMap(authUser -> productRepository.findById(productOrderRequest.getProductId())
                        .map(product -> {
                            List<ErrorDto> errors = productOrderValidation.validate(productOrderRequest);
                            if (!errors.isEmpty()) {
                                return ResponseDto.<ProductOrderResponse>builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message("ProductOrder validation error")
                                        .success(false)
                                        .errors(errors)
                                        .build();
                            }
                            Long authUserId = sessionId.getSessionId();
                            if (!authUserId.equals(productOrderRequest.getAuthUserId())) {
                                ResponseDto.<ProductResponse>builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message("AuthUser not found")
                                        .success(false)
                                        .build();
                            }
                            ProductOrder productOrder = productOrderMapper.toEntity(productOrderRequest);
                            productOrder.setAuthUser(authUser);
                            productOrder.setProduct(product);
                            productOrderRepository.save(productOrder);
                            return ResponseDto.<ProductOrderResponse>builder()
                                    .code(HttpStatus.OK.value())
                                    .message("ProductOrder successfully created")
                                    .success(true)
                                    .data(productOrderMapper.toResponse(productOrder))
                                    .build();
                        }))
                .orElseGet(() -> ResponseDto.<ProductOrderResponse>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("AuthUser or Product not found")
                        .success(false)
                        .build());
    }

    @Override
    public ResponseDto<ProductOrderResponse> getProductOrder(Long productOrderId) {
        ProductOrder productOrder = productOrderRepository.findById(productOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder not found: " + productOrderId));
        return ResponseDto.<ProductOrderResponse>builder()
                .code(HttpStatus.OK.value())
                .message("ProductOrder successfully found")
                .success(true)
                .data(productOrderMapper.toResponse(productOrder))
                .build();
    }

    @Override
    public ResponseDto<List<ProductOrderResponse>> getAllProductOrder() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        return ResponseDto.<List<ProductOrderResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("ProductOrder list successfully found")
                .success(true)
                .data(productOrders.stream().map(productOrderMapper::toResponse).toList())
                .build();
    }

    @Override
    public ResponseDto<Void> updateProductOrder(ProductOrderRequest productOrderRequest, Long productOrderId) {
        ProductOrder productOrder = productOrderRepository.findById(productOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductOrder not found: " + productOrderId));
        Product product = productRepository.findById(productOrderRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productOrderRequest.getProductId()));
        productOrder.setProduct(product);
        productOrder.setQuantity(productOrderRequest.getQuantity());
        productOrder.setTotalPrice(productOrderRequest.getTotalPrice());
        productOrderRepository.save(productOrder);
        return ResponseDto.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("ProductOrder successfully updated")
                .success(true)
                .build();
    }
}
