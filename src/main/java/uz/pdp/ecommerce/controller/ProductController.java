package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.ProductRequest;
import uz.pdp.ecommerce.response.ProductResponse;
import uz.pdp.ecommerce.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseDTO<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
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
    public ResponseDTO<Void> updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                           @PathVariable Long productId) {
        return productService.updateProduct(productRequest, productId);
    }
}
