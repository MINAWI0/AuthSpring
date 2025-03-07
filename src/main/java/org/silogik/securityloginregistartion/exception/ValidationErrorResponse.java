package org.silogik.securityloginregistartion.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ValidationErrorResponse extends ErrorResponse {
    private final List<String> errors;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error,
                                  String message, String path, List<String> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }
}