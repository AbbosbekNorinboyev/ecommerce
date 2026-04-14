package uz.pdp.ecommerce.exception;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.ecommerce.dto.ErrorDto;
import uz.pdp.ecommerce.dto.ResponseDto;

import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDto> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                    return new ErrorDto(
                            field,
                            String.format("defaultMessage: '%s', rejectedValue: '%s'", defaultMessage, rejectedValue)
                    );
                }).toList();
        return ResponseDto.<Void>builder()
                .code(HttpStatus.BAD_REQUEST.value())  // Bad request kodi
                .message("Validation error")
                .success(false)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseDto<ResourceNotFoundException> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseDto.<ResourceNotFoundException>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(resourceNotFoundException.getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseDto<UserNotFoundException> handleCustomUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseDto.<UserNotFoundException>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(userNotFoundException.getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseDto<Void> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseDto.<Void>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(badRequestException.getMessage())
                .success(false)
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseDTO<Exception> handleException(Exception exception) {
//        return ResponseDTO.<Exception>builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message("Something wrong -> " + exception.getMessage())
//                .success(false)
//                .build();
//    }
}
