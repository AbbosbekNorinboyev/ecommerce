package uz.pdp.ecommerce.dto;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDTO<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDTO> errors;
}
