package uz.pdp.ecommerce.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.dto.VerifyDTO;

public interface AuthUserService {
    ResponseEntity<String> register(RegisterDTO registerDTO);

    ResponseDto verify(VerifyDTO verifyDTO);

    ResponseEntity<String> login(LoginDTO loginDTO);

    ResponseDto checkCodeByEmail(String email, String code);

    ResponseDto checkCode(String email, String code);

    ResponseDto updatePassword(String email, String newPassword);

    ResponseDto checkUpdatePassword(String email, String newPassword, String code);

    ResponseDto updateEmail(String email, String newEmail);

    ResponseDto checkUpdateEmail(String email, String newEmail, String code);
}
