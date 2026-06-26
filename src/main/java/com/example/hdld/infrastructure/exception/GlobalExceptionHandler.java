package com.example.hdld.infrastructure.exception;

import com.example.hdld.application.dto.RootResponse;
import com.example.hdld.domain.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Translates exceptions into the HDLD platform's root-level error envelope
 * ({@code status} numeric, {@code error_code}, {@code message}) per docs/pdf_extract.txt.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static ResponseEntity<RootResponse> build(HttpStatus http, String errorCode, String message) {
        return ResponseEntity.status(http).body(new RootResponse(http.value(), errorCode, message));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<RootResponse> handleUnauthorized(UnauthorizedException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RootResponse> handleValidation(ValidationException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<RootResponse> handleConflict(ConflictException ex) {
        return build(HttpStatus.CONFLICT, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RootResponse> handleNotFound(NotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<RootResponse> handleDomain(DomainException ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getErrorCode(), ex.getMessage());
    }

    /**
     * Bean-validation failures. The PDF distinguishes E03 (a required field is missing)
     * from E02 (a field is present but malformed). We classify as E03 when any failure
     * is a "must not be blank/null" style violation, otherwise E02.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RootResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> fields = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        boolean missingRequired = ex.getBindingResult().getFieldErrors().stream()
                .anyMatch(e -> {
                    String code = e.getCode();
                    return "NotBlank".equals(code) || "NotNull".equals(code) || "NotEmpty".equals(code);
                });
        String joined = String.join(", ", fields);
        if (missingRequired) {
            return build(HttpStatus.BAD_REQUEST, "E03",
                    "Dữ liệu không đúng định dạng. Một hoặc nhiều trường dữ liệu bắt buộc bị thiếu (" + joined + ")");
        }
        return build(HttpStatus.BAD_REQUEST, "E02", "Dữ liệu không đúng định dạng. " + joined);
    }

    /** Unparseable body (bad JSON, wrong date format, etc.) — E02, not a generic E06. */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RootResponse> handleNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Malformed request body: {}", ex.getMostSpecificCause().getMessage());
        return build(HttpStatus.BAD_REQUEST, "E02", "Dữ liệu không đúng định dạng");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RootResponse> handleGeneric(Exception ex) {
        log.error("Unexpected error", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "E06", "Lỗi từ nền tảng. Vui lòng thử lại sau.");
    }
}
