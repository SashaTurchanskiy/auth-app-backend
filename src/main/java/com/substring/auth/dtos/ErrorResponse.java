package com.substring.auth.dtos;

public record ErrorResponse(
        String message,
        int status,
        String error
) {
}
