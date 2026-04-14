package uz.pdp.ecommerce.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDto<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDto> errors;
}
