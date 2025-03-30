package uz.pdp.ecommerce.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.AuthUserDTO;
import uz.pdp.ecommerce.dto.VerifyDTO;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.enums.UserStatus;

@Component
public class AuthUserMapper {

    public AuthUserDTO toDto(AuthUser user) {
        return AuthUserDTO.builder()
                .id(user.getId())
                .code(user.getCode())
                .role(user.getRole())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    public AuthUser toVerify(AuthUser authUser, VerifyDTO verifyDTO) {
        authUser.setCode(verifyDTO.getCode());
        authUser.setStatus(UserStatus.ACTIVE);
        authUser.setEmail(verifyDTO.getEmail());
        authUser.setRole(verifyDTO.getRole());
        authUser.setFullName(verifyDTO.getFullName());
        return authUser;
    }
}
