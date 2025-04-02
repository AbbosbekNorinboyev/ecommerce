package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;
import uz.pdp.ecommerce.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseDTO<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping("/{categoryId}")
    public ResponseDTO<CategoryResponse> getCategory(@PathVariable Long categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @GetMapping
    public ResponseDTO<List<CategoryResponse>> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PutMapping("/update/{categoryId}")
    public ResponseDTO<Void> updateCategory(@RequestBody @Valid CategoryRequest categoryRequest,
                                            @PathVariable Long categoryId) {
        return categoryService.updateCategory(categoryRequest, categoryId);
    }
}
