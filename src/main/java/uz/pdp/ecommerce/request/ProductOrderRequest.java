package uz.pdp.ecommerce.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.ecommerce.enums.PaymentType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductOrderRequest {
    @NotNull(message = "authUserId can not be null and empty")
    private Long authUserId;
    @NotNull(message = "productId can not be null and empty")
    private Long productId;
    private Double totalPrice;
    private Integer quantity;
    private PaymentType paymentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
