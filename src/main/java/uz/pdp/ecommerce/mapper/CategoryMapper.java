package uz.pdp.ecommerce.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .authUserId(categoryRequest.getAuthUserId())
                .createdAt(categoryRequest.getCreatedAt())
                .updatedAt(categoryRequest.getUpdatedAt())
                .build();
    }

    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .authUserId(category.getAuthUserId())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
