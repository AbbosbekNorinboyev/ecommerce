package uz.pdp.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryRequest {
    @NotBlank(message = "name can not be null and empty")
    private String name;
    @NotBlank(message = "description can not be null and empty")
    private String description;
    private Long authUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
