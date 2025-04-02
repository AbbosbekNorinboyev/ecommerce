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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "auth_user_id")
    private AuthUser authUser;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
