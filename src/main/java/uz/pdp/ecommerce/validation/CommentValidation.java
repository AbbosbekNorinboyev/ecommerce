package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.request.CommentRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentValidation {
    public List<ErrorDTO> validate(CommentRequest commentRequest) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (commentRequest.getAuthUserId() <= 0) {
            errors.add(new ErrorDTO("authUserId", "authUserId can not be negative and zero"));
        }
        if (commentRequest.getProductId() <= 0) {
            errors.add(new ErrorDTO("productId", "productId can not be negative and zero"));
        }
        return errors;
    }
}
