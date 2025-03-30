package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.dto.VerifyDTO;
import uz.pdp.ecommerce.service.impl.AuthUserServiceImpl;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:2218")
public class AuthUserController {
    private final AuthUserServiceImpl authUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return authUserService.register(registerDTO);
    }

    @PostMapping("/verify")
    public ResponseDTO verify(@RequestBody VerifyDTO verifyDTO) {
        return authUserService.verify(verifyDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        return authUserService.login(loginDTO);
    }
}