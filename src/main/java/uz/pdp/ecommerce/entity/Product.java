package uz.pdp.ecommerce.entity;

import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;
    private Long sellerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
