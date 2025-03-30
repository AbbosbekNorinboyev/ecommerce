package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterValidation {
    public List<ErrorDTO> validate(RegisterDTO registerDTO) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (!registerDTO.getEmail().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            errors.add(new ErrorDTO("email", "email is invalid"));
        }
        if (!registerDTO.getPhoneNumber().matches("^\\+998\\d{9}$")) {
            errors.add(new ErrorDTO("phoneNumber", "phoneNUmber is invalid"));
        }
        return errors;
    }
}