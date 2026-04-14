package uz.pdp.ecommerce.service;

import uz.pdp.ecommerce.dto.ResponseDto;
import uz.pdp.ecommerce.request.CommentRequest;
import uz.pdp.ecommerce.response.CommentResponse;

import java.util.List;

public interface CommentService {
    ResponseDto<CommentResponse> createComment(CommentRequest commentRequest);

    ResponseDto<CommentResponse> getComment(Long commentId);

    ResponseDto<List<CommentResponse>> getAllComment();

    ResponseDto<Void> updateComment(CommentRequest commentRequest, Long commentId);
}
