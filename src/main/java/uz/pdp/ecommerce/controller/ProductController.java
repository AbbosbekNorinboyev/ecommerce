package uz.pdp.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;
import uz.pdp.ecommerce.service.impl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @PostMapping("/create")
    public ResponseDTO<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{productId}")
    public ResponseDTO<ProductResponse> getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping
    public ResponseDTO<List<ProductResponse>> getAllProduct() {
        return productService.getAllProduct();
    }

    @PutMapping("/update/{productId}")
    public ResponseDTO<Void> updateProduct(@RequestBody ProductRequest productRequest,
                                           @PathVariable Long productId) {
        return productService.updateProduct(productRequest, productId);
    }
}
