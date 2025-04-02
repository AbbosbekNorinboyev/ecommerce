package uz.pdp.ecommerce.response;

import lombok.*;
import uz.pdp.ecommerce.enums.PaymentType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductOrderResponse {
    private Long authUserId;
    private Long productId;
    private Double totalPrice;
    private Integer quantity;
    private PaymentType paymentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
