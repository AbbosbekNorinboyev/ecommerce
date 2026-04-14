package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.request.CategoryRequest;
import uz.pdp.ecommerce.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    ResponseDto<CategoryResponse> createCategory(CategoryRequest categoryRequest);

    ResponseDto<CategoryResponse> getCategory(Long categoryId);

    ResponseDto<List<CategoryResponse>> getAllCategory();

    ResponseDto<Void> updateCategory(CategoryRequest categoryRequest, Long categoryId);
}
