package uz.pdp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerce.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}