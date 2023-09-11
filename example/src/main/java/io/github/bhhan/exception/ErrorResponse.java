package io.github.bhhan.exception;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ErrorResponse{
    private String message;
    private int status;
    private String code;
    private List<FieldError> errors = new ArrayList<>();

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = message;
    }

    public ErrorResponse(ErrorCode errorCode, List<org.springframework.validation.FieldError> fieldErrors) {
        this(errorCode);
        this.errors.addAll(fieldErrors.stream()
                .map(FieldError::new)
                .toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        @Builder
        public FieldError(org.springframework.validation.FieldError fieldError) {
            this.field = fieldError.getField();
            Object rejectedValue = fieldError.getRejectedValue();
            this.value = Objects.isNull(rejectedValue) ? "null" : rejectedValue.toString();
            this.reason = fieldError.getDefaultMessage();
        }
    }
}
