package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.request.ProductRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {
    public List<ErrorDto> validate(ProductRequest productRequest) {
        List<ErrorDto> errors = new ArrayList<>();
        if (productRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDto("authUserId", "authUserId can not be negative and zero"));
        }
        if (productRequest.getCategoryId() <= 0) {
            errors.add(new ErrorDto("categoryId", "categoryId can not be negative and zero"));
        }
        return errors;
    }
}
