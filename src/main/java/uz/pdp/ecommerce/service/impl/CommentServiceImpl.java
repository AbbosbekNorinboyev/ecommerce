package uz.pdp.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uz.pdp.ecommerce.dto.ErrorDTO;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.entity.Comment;
import uz.pdp.ecommerce.exception.ResourceNotFoundException;
import uz.pdp.ecommerce.mapper.CommentMapper;
import uz.pdp.ecommerce.repository.AuthUserRepository;
import uz.pdp.ecommerce.repository.CommentRepository;
import uz.pdp.ecommerce.repository.ProductRepository;
import uz.pdp.ecommerce.request.CommentRequest;
import uz.pdp.ecommerce.response.CategoryResponse;
import uz.pdp.ecommerce.response.CommentResponse;
import uz.pdp.ecommerce.security.SessionId;
import uz.pdp.ecommerce.service.CommentService;
import uz.pdp.ecommerce.validation.CommentValidation;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final SessionId sessionId;
    private final CommentMapper commentMapper;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final CommentValidation commentValidation;
    private final AuthUserRepository authUserRepository;

    @Override
    public ResponseDTO<CommentResponse> createComment(CommentRequest commentRequest) {
        return authUserRepository.findById(sessionId.getSessionId())
                .flatMap(authUser -> productRepository.findById(commentRequest.getProductId())
                        .map(product -> {
                            List<ErrorDTO> errors = commentValidation.validate(commentRequest);
                            if (!errors.isEmpty()) {
                                return ResponseDTO.<CommentResponse>builder()
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message("Comment validation error")
                                        .success(false)
                                        .errors(errors)
                                        .build();
                            }
                            Long authUserId = sessionId.getSessionId();
                            if (!authUserId.equals(commentRequest.getAuthUserId())) {
                                ResponseDTO.<CategoryResponse>builder()
                                        .code(HttpStatus.NOT_FOUND.value())
                                        .message("AuthUser not found")
                                        .success(false)
                                        .build();
                            }
                            Comment comment = commentMapper.toEntity(commentRequest);
                            comment.setAuthUser(authUser);
                            comment.setProduct(product);
                            commentRepository.save(comment);
                            return ResponseDTO.<CommentResponse>builder()
                                    .code(HttpStatus.OK.value())
                                    .message("Comment successfully created")
                                    .success(true)
                                    .data(commentMapper.toResponse(comment))
                                    .build();
                        }))
                .orElseGet(() -> ResponseDTO.<CommentResponse>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message("AuthUser or Product not found")
                        .success(false)
                        .build());
    }

    @Override
    public ResponseDTO<CommentResponse> getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found: " + commentId));
        return ResponseDTO.<CommentResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Comment successfully created")
                .success(true)
                .data(commentMapper.toResponse(comment))
                .build();
    }

    @Override
    public ResponseDTO<List<CommentResponse>> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return ResponseDTO.<List<CommentResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Comment list successfully found")
                .success(true)
                .data(comments.stream().map(commentMapper::toResponse).toList())
                .build();
    }

    @Override
    public ResponseDTO<Void> updateComment(CommentRequest commentRequest, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found: " + commentId));
        comment.setDescription(commentRequest.getDescription());
        return ResponseDTO.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Comment successfully updated")
                .success(true)
                .build();
    }
}
