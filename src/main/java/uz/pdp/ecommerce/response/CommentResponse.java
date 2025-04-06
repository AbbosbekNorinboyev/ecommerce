package uz.pdp.ecommerce.response;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentResponse {
    private String description;
    private Long authUserId;
    private Long productId;
    private LocalDateTime createdAt;
}