package uz.pdp.ecommerce.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductResponse {
    private String name;
    private String description;
    private Double price;
    private Long authUserId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
