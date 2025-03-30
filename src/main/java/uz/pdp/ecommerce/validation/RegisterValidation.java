package uz.pdp.ecommerce.validation;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterValidation {
    public List<ErrorDTO> validate(RegisterDTO registerCreateDTO) {
        List<ErrorDTO> errors = new ArrayList<>();
        if (!registerCreateDTO.getUsername().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            errors.add(new ErrorDTO("username", "username is invalid"));
        }
        if (!registerCreateDTO.getPhoneNumber().matches("^\\+998\\d{9}$")) {
            errors.add(new ErrorDTO("phoneNumber", "phoneNUmber is invalid"));
        }
        return errors;
    }
}