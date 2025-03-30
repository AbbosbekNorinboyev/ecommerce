package uz.pdp.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.ecommerce.enums.Roles;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class RegisterDTO {
    @NotBlank(message = "username can not be null and empty")
    private String username;
    @NotBlank(message = "password can not be null and empty")
    private String password;
    @NotBlank(message = "fullName can not be null and empty")
    private String fullName;
    @NotBlank(message = "phoneNumber can not be null and empty")
    private String phoneNumber;
    @NotBlank(message = "email can not be null and empty")
    private String email;
}
