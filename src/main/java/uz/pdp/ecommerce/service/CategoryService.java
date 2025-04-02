package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    ResponseDTO<CategoryResponse> createCategory(CategoryRequest categoryRequest);

    ResponseDTO<CategoryResponse> getCategory(Long categoryId);

    ResponseDTO<List<CategoryResponse>> getAllCategory();

    ResponseDTO<Void> updateCategory(CategoryRequest categoryRequest, Long categoryId);
}
