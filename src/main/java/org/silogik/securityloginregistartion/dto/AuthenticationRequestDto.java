package org.silogik.securityloginregistartion.dto;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
