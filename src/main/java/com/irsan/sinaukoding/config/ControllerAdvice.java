package com.irsan.sinaukoding.config;

import com.irsan.sinaukoding.model.FieldError;
import com.irsan.sinaukoding.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> new FieldError(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.error("Validation Error", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleGlobalException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.ok(BaseResponse.error("Terjadi Kesalahan di Server"));
    }

}
