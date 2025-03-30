package uz.pdp.ecommerce.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;

public interface AuthUserService {
    ResponseEntity<String> register(RegisterDTO registerDTO);

    //    ResponseDTO verify(VerifyDTO verifyDTO);
    ResponseEntity<String> login(LoginDTO loginDTO);

}
