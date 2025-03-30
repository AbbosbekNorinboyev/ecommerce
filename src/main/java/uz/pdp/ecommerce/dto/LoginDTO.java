package uz.pdp.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class LoginDTO {
    @NotBlank(message = "username is not be null and empty")
    private String username;
    @NotBlank(message = "password can not be null and empty")
    private String password;
}
