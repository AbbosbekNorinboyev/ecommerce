package uz.pdp.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.ecommerce.enums.PaymentType;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser authUser;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Double totalPrice;
    private Integer quantity;
    private PaymentType paymentType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
