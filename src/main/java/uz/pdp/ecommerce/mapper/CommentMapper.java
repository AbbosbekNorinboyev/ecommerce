package uz.pdp.ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.entity.AuthUser;
import uz.pdp.ecommerce.entity.Comment;
import uz.pdp.ecommerce.entity.Product;
import uz.pdp.ecommerce.exception.CustomUserNotFoundException;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.ProductRepository;
import uz.pdp.ecommerce.request.CommentRequest;
import uz.pdp.ecommerce.response.CommentResponse;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final AuthUserRepository authUserRepository;
    private final ProductRepository productRepository;

    public Comment toEntity(CommentRequest commentRequest) {
        AuthUser authUser = authUserRepository.findById(commentRequest.getAuthUserId())
                .orElseThrow(() -> new CustomUserNotFoundException("AuthUser not found: " + commentRequest.getAuthUserId()));
        Product product = productRepository.findById(commentRequest.getProductId())
                .orElseThrow(() -> new CustomUserNotFoundException("Product not found: " + commentRequest.getProductId()));
        return Comment.builder()
                .authUser(authUser)
                .product(product)
                .description(commentRequest.getDescription())
                .createdAt(commentRequest.getCreatedAt())
                .build();
    }

    public CommentResponse toResponse(Comment comment) {
        return CommentResponse.builder()
                .authUserId(comment.getAuthUser().getId())
                .productId(comment.getProduct().getId())
                .description(comment.getDescription())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
