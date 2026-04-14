package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ResponseDto<ProductResponse> createProduct(ProductRequest productRequest);

    ResponseDto<ProductResponse> getProduct(Long productId);

    ResponseDto<List<ProductResponse>> getAllProduct();

    ResponseDto<Void> updateProduct(ProductRequest productRequest, Long productId);
}
