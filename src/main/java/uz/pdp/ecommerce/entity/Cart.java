package uz.pdp.ecommerce.entity;

import java.time.LocalDateTime;

public class Cart {
    private Long id;
    private Long authUserId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
