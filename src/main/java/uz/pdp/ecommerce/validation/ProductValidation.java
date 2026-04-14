package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.request.ProductRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {
    public List<ErrorDTO> validate(ProductRequest productRequest) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (productRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDTO("authUserId", "authUserId can not be negative and zero"));
        }
        if (productRequest.getCategoryId() <= 0) {
            errors.add(new ErrorDTO("categoryId", "categoryId can not be negative and zero"));
        }
        return errors;
    }
}
