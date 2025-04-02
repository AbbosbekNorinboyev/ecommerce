package uz.pdp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}