package uz.pdp.ecommerce.dto;

import lombok.*;
import uz.pdp.ecommerce.enums.Roles;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class VerifyDTO {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String code;
    private Roles role;
}
