package uz.pdp.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.exception.CustomUserNotFoundException;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final AuthUserRepository authUserRepository;

    public Category toEntity(CategoryRequest categoryRequest) {
        AuthUser authUser = authUserRepository.findById(categoryRequest.getAuthUserId())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found: " + categoryRequest.getAuthUserId()));
        return Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .authUser(authUser)
                .createdAt(categoryRequest.getCreatedAt())
                .updatedAt(categoryRequest.getUpdatedAt())
                .build();
    }

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .authUserId(category.getAuthUser().getId())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
