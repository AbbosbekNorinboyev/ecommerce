package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ResponseDTO<ProductResponse> createProduct(ProductRequest productRequest);

    ResponseDTO<ProductResponse> getProduct(Long productId);

    ResponseDTO<List<ProductResponse>> getAllProduct();

    ResponseDTO<Void> updateProduct(ProductRequest productRequest, Long productId);
}
