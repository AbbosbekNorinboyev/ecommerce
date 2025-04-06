package uz.pdp.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommerce.dto.ResponseDTO;
import uz.pdp.ecommerce.request.CommentRequest;
import uz.pdp.ecommerce.response.CommentResponse;
import uz.pdp.ecommerce.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseDTO<CommentResponse> createComment(@RequestBody @Valid CommentRequest commentRequest) {
        return commentService.createComment(commentRequest);
    }

    @GetMapping("/{commentId}")
    public ResponseDTO<CommentResponse> getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping
    public ResponseDTO<List<CommentResponse>> getAllComment() {
        return commentService.getAllComment();
    }

    @PutMapping("/update/{commentId}")
    public ResponseDTO<Void> updateComment(@RequestBody @Valid CommentRequest commentRequest,
                                           @PathVariable Long commentId) {
        return commentService.updateComment(commentRequest, commentId);
    }
}
