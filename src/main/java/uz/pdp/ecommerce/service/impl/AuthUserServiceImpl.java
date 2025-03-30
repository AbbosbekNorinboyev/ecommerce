package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.dto.LoginDTO;
import uz.pdp.ecommerce.dto.RegisterDTO;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.enums.Roles;
import uz.pdp.ecommerce.exception.CustomUserNotFoundException;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.security.CustomUserDetailsService;
import uz.pdp.ecommerce.service.AuthUserService;
import uz.pdp.ecommerce.utils.JWTUtil;
import uz.pdp.ecommerce.validation.RegisterValidation;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final JWTUtil jwtUtil;
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
        AuthUser authUser = AuthUser.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .fullName(registerDTO.getFullName())
                .phoneNumber(registerDTO.getPhoneNumber())
                .role(Roles.USER)
                .build();
        authUserRepository.save(authUser);
        return ResponseEntity.ok("AuthUser successfully register");
    }

//    @Override
//    public ResponseDTO verify(VerifyDTO verifyDTO) {
//        AuthUser authUser = authUserRepository.findById(verifyDTO.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("AuthUser not found: " + verifyDTO.getId()));
//        if (authUser.getCode() != null && verifyDTO.getCode() != null && authUser.getCode().equals(verifyDTO.getCode())) {
//            if (!utils.checkEmail(verifyDTO.getEmail())) {
//                return ResponseDTO.builder()
//                        .code(HttpStatus.BAD_REQUEST.value())
//                        .message("Email is invalid")
//                        .success(false)
//                        .build();
//            }
//            if (!authUser.getEmail().equals(verifyDTO.getEmail())) {
//                return ResponseDTO.builder()
//                        .code(HttpStatus.BAD_REQUEST.value())
//                        .message("Email is not valid")
//                        .success(false)
//                        .build();
//            }
//            if (!authUser.getFullName().equals(verifyDTO.getFullName())) {
//                return ResponseDTO.builder()
//                        .code(HttpStatus.BAD_REQUEST.value())
//                        .message("FullName is invalid")
//                        .success(false)
//                        .build();
//            }
//            AuthUser authUserVerify = authUserMapper.toVerify(authUser, verifyDTO);
//            authUserVerify.setPassword(passwordEncoder.encode(verifyDTO.getPassword()));
//            authUserVerify.setCreatedAt(LocalDateTime.now());
//            authUserVerify.setIsLogin(true);
//            AuthUser savedAuthUser = authUserRepository.save(authUserVerify);
//            return ResponseDTO.builder()
//                    .code(HttpStatus.OK.value())
//                    .message("Successfully verified")
//                    .success(true)
//                    .data(authUserMapper.toDto(savedAuthUser))
//                    .build();
//        }
//        return ResponseDTO.builder()
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message("CODE IS ERROR")
//                .success(false)
//                .build();
//    }

    @Override
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        AuthUser authUser = authUserRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found by username: " + loginDTO.getUsername()));
        if (authUser.getUsername() == null) {
            return ResponseEntity.ok().body("Username not found");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDTO.getUsername());
        String jwtGenerateToken = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwtGenerateToken);
    }
}
