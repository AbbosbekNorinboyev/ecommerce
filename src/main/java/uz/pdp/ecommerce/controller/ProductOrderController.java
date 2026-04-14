package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDto;
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
    public ResponseDto<ProductOrderResponse> createProductOrder(@RequestBody @Valid ProductOrderRequest productOrderRequest) {
        return productOrderService.createProductOrder(productOrderRequest);
    }

    @GetMapping("/{productOrderId}")
    public ResponseDto<ProductOrderResponse> getProductOrder(@PathVariable Long productOrderId) {
        return productOrderService.getProductOrder(productOrderId);
    }

    @GetMapping
    public ResponseDto<List<ProductOrderResponse>> getAllProductOrder() {
        return productOrderService.getAllProductOrder();
    }

    @PutMapping("/update/{productOrderId}")
    public ResponseDto<Void> updateProductOrder(@RequestBody @Valid ProductOrderRequest productOrderRequest,
                                                @PathVariable Long productOrderId) {
        return productOrderService.updateProductOrder(productOrderRequest, productOrderId);
    }

    @PutMapping("/update/status/{productOrderId}")
    public ResponseDto<Void> updateOrderStatus(@PathVariable Long productOrderId,
                                               @RequestParam String status) {
        return productOrderService.updateOrderStatus(productOrderId, status);
    }
}
