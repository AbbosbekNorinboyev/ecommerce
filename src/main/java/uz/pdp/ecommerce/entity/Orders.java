package uz.pdp.ecommerce.entity;

import java.time.LocalDateTime;

public class Orders {
    private Long id;
    private Long authUserId;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
