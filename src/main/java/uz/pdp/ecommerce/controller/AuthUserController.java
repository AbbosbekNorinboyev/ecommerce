package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.dto.VerifyDTO;
import uz.pdp.ecommerce.service.AuthUserService;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:2218")
public class AuthUserController {
    private final AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return authUserService.register(registerDTO);
    }

    @PostMapping("/verify")
    public ResponseDto verify(@RequestBody VerifyDTO verifyDTO) {
        return authUserService.verify(verifyDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        return authUserService.login(loginDTO);
    }

    @PostMapping("/checkCodeByEmailInLogin")
    public ResponseDto checkCodeByEmailInLogin(@RequestParam String email, @RequestParam String code) {
        return authUserService.checkCodeByEmail(email, code);
    }

    @PostMapping("/checkCode")
    public ResponseDto checkCode(@RequestParam String email, @RequestParam String code) {
        return authUserService.checkCode(email, code);
    }

    @PostMapping("/updatePassword")
    public ResponseDto updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        return authUserService.updatePassword(email, newPassword);
    }

    @PostMapping("/checkUpdatePassword")
    public ResponseDto checkUpdatePassword(@RequestParam String email,
                                           @RequestParam String newPassword,
                                           @RequestParam String code) {
        return authUserService.checkUpdatePassword(email, newPassword, code);
    }

    @PostMapping("/updateEmail")
    public ResponseDto updateEmail(@RequestParam String email, @RequestParam String newEmail) {
        return authUserService.updateEmail(email, newEmail);
    }

    @PostMapping("/checkUpdateEmail")
    public ResponseDto checkUpdateEmail(@RequestParam String email, @RequestParam String newEmail,
                                        @RequestParam String code) {
        return authUserService.checkUpdateEmail(email, newEmail, code);
    }
}