package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.request.ProductOrderRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductOrderValidation {
    public List<ErrorDTO> validate(ProductOrderRequest productOrderRequest) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (productOrderRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDTO("authUserId", "authUserId can not be negative and zero"));
        }
        if (productOrderRequest.getProductId() <= 0) {
            errors.add(new ErrorDTO("productId", "productId can not be negative and zero"));
        }
        return errors;
    }
}
