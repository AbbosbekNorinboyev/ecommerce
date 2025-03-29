package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.ecommerce.Utils.Utils;
import uz.pdp.ecommerce.dto.AuthUserRegisterDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.mapper.AuthUserMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.service.AuthUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final Utils utils;
    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;

    @Override
    public ResponseDTO<AuthUserRegisterDTO> register(AuthUserRegisterDTO authUserRegisterDTO) {
        AuthUser authUser = authUserRepository.findByUsername(authUserRegisterDTO.getUsername())
                .orElse(null);
        if (authUser != null) {
            return ResponseDTO.<AuthUserRegisterDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("USERNAME ALREADY EXISTS")
                    .success(false)
                    .build();
        }
        if (!utils.checkEmail(authUserRegisterDTO.getEmail())) {
            return ResponseDTO.<AuthUserRegisterDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("Email is invalid")
                    .success(false)
                    .build();
        }
        if (!utils.checkPhoneNumber(authUserRegisterDTO.getPhoneNumber())) {
            return ResponseDTO.<AuthUserRegisterDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message("PhoneNumber is invalid")
                    .success(false)
                    .build();
        }
        AuthUser authUserEntity = authUserMapper.toEntity(authUserRegisterDTO);
        authUserEntity.setCreatedAt(LocalDateTime.now());
        authUserRepository.save(authUserEntity);
        return ResponseDTO.<AuthUserRegisterDTO>builder()
                .code(HttpStatus.OK.value())
                .message("AuthUser successfully registered")
                .success(true)
                .build();
    }
}
