package uz.pdp.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser authUser;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private LocalDateTime createdAt;
}