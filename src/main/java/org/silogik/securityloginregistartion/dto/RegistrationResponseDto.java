package org.silogik.securityloginregistartion.dto;

public record RegistrationResponseDto(
        String username,
        String email,
        boolean emailVerificationRequired

) {
}