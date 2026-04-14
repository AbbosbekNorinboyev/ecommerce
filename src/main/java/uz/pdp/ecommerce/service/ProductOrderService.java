package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.request.ProductOrderRequest;
import uz.pdp.ecommerce.response.ProductOrderResponse;

import java.util.List;

public interface ProductOrderService {
    ResponseDto<ProductOrderResponse> createProductOrder(ProductOrderRequest productOrderRequest);

    ResponseDto<ProductOrderResponse> getProductOrder(Long productOrderId);

    ResponseDto<List<ProductOrderResponse>> getAllProductOrder();

    ResponseDto<Void> updateProductOrder(ProductOrderRequest productOrderRequest, Long productOrderId);
}
