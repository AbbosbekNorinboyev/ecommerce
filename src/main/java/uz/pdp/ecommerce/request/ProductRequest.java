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
public class ProductRequest {
    @NotBlank(message = "name can not be null and empty")
    private String name;
    @NotBlank(message = "description can not be null and empty")
    private String description;
    @NotBlank(message = "price can not be null and empty")
    private Double price;
    @NotBlank(message = "authUserId can not be null and empty")
    private Long authUserId;
    @NotBlank(message = "categoryId can not be null and empty")
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}