package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.request.ProductOrderRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductOrderValidation {
    public List<ErrorDto> validate(ProductOrderRequest productOrderRequest) {
        List<ErrorDto> errors = new ArrayList<>();
        if (productOrderRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDto("authUserId", "authUserId can not be negative and zero"));
        }
        if (productOrderRequest.getProductId() <= 0) {
            errors.add(new ErrorDto("productId", "productId can not be negative and zero"));
        }
        return errors;
    }
}
