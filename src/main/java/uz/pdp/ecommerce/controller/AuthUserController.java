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

    @PostMapping("/checkCodeByEmailInLogin")
    public ResponseDTO checkCodeByEmailInLogin(@RequestParam String email, @RequestParam String code) {
        return authUserService.checkCodeByEmail(email, code);
    }

    @PostMapping("/checkCode")
    public ResponseDTO checkCode(@RequestParam String email, @RequestParam String code) {
        return authUserService.checkCode(email, code);
    }

    @PostMapping("/updatePassword")
    public ResponseDTO updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        return authUserService.updatePassword(email, newPassword);
    }
    @PostMapping("/checkUpdatePassword")
    public ResponseDTO checkUpdatePassword(@RequestParam String email, @RequestParam String newPassword,
                                        @RequestParam String code) {
        return authUserService.checkUpdatePassword(email, newPassword, code);
    }
    @PostMapping("/updateEmail")
    public ResponseDTO updateEmail(@RequestParam String email, @RequestParam String newEmail) {
        return authUserService.updateEmail(email, newEmail);
    }

    @PostMapping("/checkUpdateEmail")
    public ResponseDTO checkUpdateEmail(@RequestParam String email, @RequestParam String newEmail,
                                        @RequestParam String code) {
        return authUserService.checkUpdateEmail(email, newEmail, code);
    }
}