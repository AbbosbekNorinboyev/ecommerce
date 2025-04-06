package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.CommentRequest;
import uz.pdp.ecommerce.response.CommentResponse;

import java.util.List;

public interface CommentService {
    ResponseDTO<CommentResponse> createComment(CommentRequest commentRequest);

    ResponseDTO<CommentResponse> getComment(Long commentId);

    ResponseDTO<List<CommentResponse>> getAllComment();

    ResponseDTO<Void> updateComment(CommentRequest commentRequest, Long commentId);
}
