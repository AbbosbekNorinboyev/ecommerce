package uz.pdp.ecommerce.dto;

import lombok.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class ErrorDTO {
    private String field;
    private String message;
}
