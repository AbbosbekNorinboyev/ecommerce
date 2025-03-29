package uz.pdp.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.ecommerce.enums.Roles;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthUserRegisterDTO {
    @NotBlank(message = "fullName is not null and empty")
    private String fullName;
    @NotBlank(message = "email can not be null and empty")
    private String email;
    @NotBlank(message = "username is not be null and empty")
    private String username;
    @NotBlank(message = "password can not be null and empty")
    private String password;
    @NotBlank(message = "phoneNumber can not be null and empty")
    private String phoneNumber;
    private Roles role;
}
