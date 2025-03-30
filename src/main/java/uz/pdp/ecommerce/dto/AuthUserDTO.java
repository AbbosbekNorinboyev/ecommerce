package uz.pdp.ecommerce.dto;

import lombok.*;
import uz.pdp.ecommerce.enums.Roles;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class AuthUserDTO {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String code;
    private Roles role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    public static class UpdateUser {
        private Long id;
        private String fullName;
        private String phoneNumber;
    }
}
