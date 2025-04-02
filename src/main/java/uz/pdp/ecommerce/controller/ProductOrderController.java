package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.ProductOrderRequest;
import uz.pdp.ecommerce.response.ProductOrderResponse;
import uz.pdp.ecommerce.service.ProductOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/productOrders")
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @PostMapping("/create")
    public ResponseDTO<ProductOrderResponse> createProductOrder(@RequestBody @Valid ProductOrderRequest productOrderRequest) {
        return productOrderService.createProductOrder(productOrderRequest);
    }

    @GetMapping("/{productOrderId}")
    public ResponseDTO<ProductOrderResponse> getProductOrder(@PathVariable Long productOrderId) {
        return productOrderService.getProductOrder(productOrderId);
    }

    @GetMapping
    public ResponseDTO<List<ProductOrderResponse>> getAllProductOrder() {
        return productOrderService.getAllProductOrder();
    }

    @PutMapping("/update/{productOrderId}")
    public ResponseDTO<Void> updateProductOrder(@RequestBody @Valid ProductOrderRequest productOrderRequest,
                                                @PathVariable Long productOrderId) {
        return productOrderService.updateProductOrder(productOrderRequest, productOrderId);
    }
}
