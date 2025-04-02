package uz.pdp.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.exception.CustomUserNotFoundException;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.CategoryRepository;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final AuthUserRepository authUserRepository;
    private final CategoryRepository categoryRepository;

    public Product toEntity(ProductRequest productRequest) {
        AuthUser authUser = authUserRepository.findById(productRequest.getAuthUserId())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found: " + productRequest.getAuthUserId()));
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + productRequest.getCategoryId()));
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .authUser(authUser)
                .category(category)
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .authUserId(product.getAuthUser().getId())
                .categoryId(product.getCategory().getId())
                .build();
    }
}
