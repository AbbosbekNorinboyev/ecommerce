package uz.pdp.ecommerce.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentRequest {
    @NotBlank(message = "description can not be null and empty")
    private String description;
    @NotNull(message = "authUserId can not be null and empty")
    private Long authUserId;
    @NotNull(message = "productId can not be null and empty")
    private Long productId;
    private LocalDateTime createdAt;
}