package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.dto.*;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.enums.Roles;
import uz.pdp.ecommerce.exception.CustomUserNotFoundException;
import uz.pdp.ecommerce.mapper.AuthUserMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.security.CustomUserDetailsService;
import uz.pdp.ecommerce.service.AuthUserService;
import uz.pdp.ecommerce.utils.JWTUtil;
import uz.pdp.ecommerce.utils.Utils;
import uz.pdp.ecommerce.validation.RegisterValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final Utils utils;
    private final JWTUtil jwtUtil;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository authUserRepository;
    private final RegisterValidation registerValidation;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public ResponseEntity<String> register(RegisterDTO registerDTO) {
        Optional<AuthUser> byUsername = authUserRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        List<ErrorDTO> errors = registerValidation.validate(registerDTO);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body("Validation error");
        }
        String code = utils.getCode();
        AuthUser authUser = AuthUser.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .fullName(registerDTO.getFullName())
                .phoneNumber(registerDTO.getPhoneNumber())
                .role(Roles.USER)
                .code(code)
                .email(registerDTO.getEmail())
                .createdAt(LocalDateTime.now())
                .build();
        authUserRepository.save(authUser);
        return ResponseEntity.ok("AuthUser successfully register");
    }

    @Override
    public ResponseDTO verify(VerifyDTO verifyDTO) {
        AuthUser authUser = authUserRepository.findById(verifyDTO.getId())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found: " + verifyDTO.getId()));
        if (authUser.getCode() != null && verifyDTO.getCode() != null && authUser.getCode().equals(verifyDTO.getCode())) {
            if (!utils.checkEmail(verifyDTO.getEmail())) {
                return ResponseDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Email is invalid")
                        .success(false)
                        .build();
            }
            if (!authUser.getEmail().equals(verifyDTO.getEmail())) {
                return ResponseDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Email is not valid")
                        .success(false)
                        .build();
            }
            if (!authUser.getFullName().equals(verifyDTO.getFullName())) {
                return ResponseDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("FullName is invalid")
                        .success(false)
                        .build();
            }
            AuthUser authUserVerify = authUserMapper.toVerify(authUser, verifyDTO);
            authUserVerify.setCreatedAt(LocalDateTime.now());
            authUserVerify.setIsLogin(false);
            AuthUser savedAuthUser = authUserRepository.save(authUserVerify);
            return ResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                    .message("Successfully verified")
                    .success(true)
                    .data(authUserMapper.toDto(savedAuthUser))
                    .build();
        }
        return ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("CODE IS ERROR")
                .success(false)
                .build();
    }

    @Override
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        AuthUser authUser = authUserRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by username: " + loginDTO.getUsername()));
        if (authUser.getUsername() == null) {
            return ResponseEntity.badRequest().body("Username not found");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDTO.getUsername());
        String jwtGenerateToken = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwtGenerateToken);
    }

    @Override
    public ResponseDTO checkCodeByEmail(String email, String code) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found bu email: " + email));
        if (authUser.getCode() != null && authUser.getCode().equals(code)) {
            authUser.setIsLogin(true);
            authUser.setLoginAt(LocalDateTime.now());
            authUserRepository.save(authUser);
            return ResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                    .message("Successfully checked")
                    .success(true)
                    .data(authUser)
                    .build();
        }
        return ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Code is invalid")
                .success(false)
                .build();
    }

    @Override
    public ResponseDTO checkCode(String email, String code) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by email: " + email));
        if (authUser.getCode() != null && authUser.getCode().equals(code)) {
            return ResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                    .message("Code is successfully checked")
                    .success(true)
                    .data(authUser)
                    .build();
        }
        return ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Code is invalid")
                .success(false)
                .build();
    }

    @Override
    public ResponseDTO updatePassword(String email, String newPassword) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by email: " + email));
        String encodePassword = passwordEncoder.encode(newPassword);
        authUser.setPassword(encodePassword);
        authUserRepository.save(authUser);
        System.out.println("authUser = " + authUser);
        return ResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .message("Password is successfully updated")
                .success(true)
                .data(authUser)
                .build();
    }

    @Override
    public ResponseDTO checkUpdatePassword(String email, String newPassword, String code) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by email: " + email));
        if (authUser.getCode() != null && authUser.getCode().equals(code)) {
            authUser.setCode(code);
            authUser.setPassword(passwordEncoder.encode(newPassword));
            authUserRepository.save(authUser);
            return ResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                    .message("New Password is successfully checked")
                    .success(true)
                    .data(authUser)
                    .build();
        }
        return ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Code is invalid")
                .success(false)
                .build();
    }

    @Override
    public ResponseDTO updateEmail(String email, String newEmail) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by email: " + email));
        authUser.setEmail(newEmail);
        authUserRepository.save(authUser);
        return ResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .message("Email is successfully updated")
                .success(true)
                .data(authUser)
                .build();
    }

    @Override
    public ResponseDTO checkUpdateEmail(String email, String newEmail, String code) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by email: " + email));
        if (authUser.getCode() != null && authUser.getCode().equals(code)) {
            authUser.setCode(code);
            authUser.setEmail(newEmail);
            authUserRepository.save(authUser);
            return ResponseDTO.builder()
                    .code(HttpStatus.OK.value())
                    .message("New Email is successfully checked")
                    .success(true)
                    .data(authUser)
                    .build();
        }
        return ResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Code is invalid")
                .success(false)
                .build();
    }
}
