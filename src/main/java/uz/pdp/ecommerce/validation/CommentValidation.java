package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.request.CommentRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentValidation {
    public List<ErrorDto> validate(CommentRequest commentRequest) {
        List<ErrorDto> errors = new ArrayList<>();
        if (commentRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDto("authUserId", "authUserId can not be negative and zero"));
        }
        if (commentRequest.getProductId() <= 0) {
            errors.add(new ErrorDto("productId", "productId can not be negative and zero"));
        }
        return errors;
    }
}
