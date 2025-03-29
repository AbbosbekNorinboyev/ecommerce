package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.ecommerce.dto.AuthUserRegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.service.impl.AuthUserServiceImpl;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthUserController {
    private final AuthUserServiceImpl authUserService;

    @PostMapping("/register")
    public ResponseDTO<AuthUserRegisterDTO> register(@RequestBody @Valid AuthUserRegisterDTO authUserRegisterDTO) {
        return authUserService.register(authUserRegisterDTO);
    }
}
