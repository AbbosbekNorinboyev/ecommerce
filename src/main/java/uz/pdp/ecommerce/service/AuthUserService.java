package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.AuthUserRegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;

public interface AuthUserService {
    ResponseDTO<AuthUserRegisterDTO> register(AuthUserRegisterDTO authUserRegisterDTO);
}
