package uz.pdp.ecommerce.entity;

import java.time.LocalDateTime;

public class Payments {
    private Long id;
    private Long orderId;
    private Long authUserId;
    private Double amount;
    private String status;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
