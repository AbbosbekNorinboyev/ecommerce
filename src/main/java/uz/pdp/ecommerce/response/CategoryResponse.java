package uz.pdp.ecommerce.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponse {
    private String name;
    private String description;
    private Long authUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
