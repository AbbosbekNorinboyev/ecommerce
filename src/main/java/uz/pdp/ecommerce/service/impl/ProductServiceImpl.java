package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.mapper.ProductMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.CategoryRepository;
import uz.pdp.ecommerce.repository.ProductRepository;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;
import uz.pdp.ecommerce.security.SessionId;
import uz.pdp.ecommerce.service.ProductService;
import uz.pdp.ecommerce.validation.ProductValidation;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final SessionId sessionId;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductValidation productValidation;
    private final AuthUserRepository authUserRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseDTO<ProductResponse> createProduct(ProductRequest productRequest) {
        return authUserRepository.findById(sessionId.getSessionId())
                .flatMap(authUser -> categoryRepository.findById(productRequest.getCategoryId())
                        .map(category -> {
                            List<ErrorDTO> errors = productValidation.validate(productRequest);
                            if (!errors.isEmpty()) {
                                ResponseDTO.<ProductResponse>builder()
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message("Product validation error")
                                        .success(false)
                                        .errors(errors)
                                        .build();
                            }
                            Long authUserId = sessionId.getSessionId();
                            if (!authUserId.equals(productRequest.getAuthUserId())) {
                                ResponseDTO.<ProductResponse>builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message("AuthUser not found")
                                        .success(false)
                                        .build();
                            }
                            Product product = productMapper.toEntity(productRequest);
                            product.setAuthUser(authUser);
                            product.setCategory(category);
                            productRepository.save(product);
                            return ResponseDTO.<ProductResponse>builder()
                                    .code(HttpStatus.OK.value())
                                    .message("Product successfully created")
                                    .success(true)
                                    .data(productMapper.toResponse(product))
                                    .build();
                        }))
                .orElseGet(() -> ResponseDTO.<ProductResponse>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("AuthUser or Category not found")
                        .success(false)
                        .build());
    }

    @Override
    public ResponseDTO<ProductResponse> getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        return ResponseDTO.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Product successfully found")
                .success(true)
                .data(productMapper.toResponse(product))
                .build();
    }

    @Override
    public ResponseDTO<List<ProductResponse>> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return ResponseDTO.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Product successfully created")
                .success(true)
                .data(products.stream().map(productMapper::toResponse).toList())
                .build();
    }

    @Override
    public ResponseDTO<Void> updateProduct(ProductRequest productRequest, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + productRequest.getCategoryId()));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return ResponseDTO.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product successfully updated")
                .success(true)
                .build();
    }
}
