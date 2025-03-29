package uz.pdp.ecommerce.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.AuthUserRegisterDTO;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.enums.Roles;

@Component
public class AuthUserMapper {
    public AuthUser toEntity(AuthUserRegisterDTO authUserRegisterDTO) {
        return AuthUser.builder()
                .fullName(authUserRegisterDTO.getFullName())
                .username(authUserRegisterDTO.getUsername())
                .password(authUserRegisterDTO.getPassword())
                .email(authUserRegisterDTO.getEmail())
                .phoneNumber(authUserRegisterDTO.getPhoneNumber())
                .role(Roles.BUYER)
                .build();
    }
}
