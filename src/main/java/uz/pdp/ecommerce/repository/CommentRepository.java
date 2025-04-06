package uz.pdp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerce.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}