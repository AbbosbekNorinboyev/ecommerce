package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.ProductOrderRequest;
import uz.pdp.ecommerce.response.ProductOrderResponse;

import java.util.List;

public interface ProductOrderService {
    ResponseDTO<ProductOrderResponse> createProductOrder(ProductOrderRequest productOrderRequest);

    ResponseDTO<ProductOrderResponse> getProductOrder(Long productOrderId);

    ResponseDTO<List<ProductOrderResponse>> getAllProductOrder();

    ResponseDTO<Void> updateProductOrder(ProductOrderRequest productOrderRequest, Long productOrderId);
}
