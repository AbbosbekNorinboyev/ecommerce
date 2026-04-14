package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.request.CategoryRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {
    public List<ErrorDto> validate(CategoryRequest categoryRequest) {
        List<ErrorDto> errors = new ArrayList<>();
        if (categoryRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDto("authUserId", "authUserId can not be negative and zero"));
        }
        return errors;
    }
}
