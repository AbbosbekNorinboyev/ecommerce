package uz.pdp.ecommerce.entity;

import java.time.LocalDateTime;

public class Review {
    private Long id;
    private Long authUserId;
    private Long productId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
