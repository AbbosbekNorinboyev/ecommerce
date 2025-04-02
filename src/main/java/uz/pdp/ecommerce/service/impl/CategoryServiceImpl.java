package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.entity.Category;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.mapper.CategoryMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.CategoryRepository;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;
import uz.pdp.ecommerce.security.SessionId;
import uz.pdp.ecommerce.service.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final AuthUserRepository authUserRepository;
    private final SessionId sessionId;

    @Override
    public ResponseDTO<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        return authUserRepository.findById(sessionId.getSessionId())
                .map(authUser -> {
                    Category category = categoryMapper.toEntity(categoryRequest);
                    category.setAuthUser(authUser);
                    categoryRepository.save(category);
                    return ResponseDTO.<CategoryResponse>builder()
                            .code(HttpStatus.OK.value())
                            .message("Category successfully created")
                            .success(true)
                            .data(categoryMapper.toResponse(category))
                            .build();
                })
                .orElseGet(() -> ResponseDTO.<CategoryResponse>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("AuthUser not found: " + categoryRequest.getAuthUserId())
                        .success(false)
                        .build());
    }


    @Override
    public ResponseDTO<CategoryResponse> getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        return ResponseDTO.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Category successfully found")
                .success(true)
                .data(categoryMapper.toResponse(category))
                .build();
    }

    @Override
    public ResponseDTO<List<CategoryResponse>> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseDTO.<List<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Category list successfully created")
                .success(true)
                .data(categories.stream().map(categoryMapper::toResponse).toList())
                .build();
    }

    @Override
    public ResponseDTO<Void> updateCategory(CategoryRequest categoryRequest, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);
        return ResponseDTO.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Category successfully updated")
                .success(true)
                .build();
    }
}
