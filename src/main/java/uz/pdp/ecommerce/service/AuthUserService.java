package uz.pdp.ecommerce.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.dto.VerifyDTO;

public interface AuthUserService {
    ResponseEntity<String> register(RegisterDTO registerDTO);

    ResponseDTO verify(VerifyDTO verifyDTO);

    ResponseEntity<String> login(LoginDTO loginDTO);

    ResponseDTO checkCodeByEmail(String email, String code);

    ResponseDTO checkCode(String email, String code);

    ResponseDTO updatePassword(String email, String newPassword);

    ResponseDTO checkUpdatePassword(String email, String newPassword, String code);

    ResponseDTO updateEmail(String email, String newEmail);
    ResponseDTO checkUpdateEmail(String email, String newEmail, String code);

}
