package org.silogik.securityloginregistartion.controller;

import lombok.RequiredArgsConstructor;
import org.silogik.securityloginregistartion.dto.AuthenticationRequestDto;
import org.silogik.securityloginregistartion.dto.AuthenticationResponseDto;
import org.silogik.securityloginregistartion.dto.UserProfileDto;
import org.silogik.securityloginregistartion.mapper.UserMapper;
import org.silogik.securityloginregistartion.service.AuthenticationService;
import org.silogik.securityloginregistartion.service.EmailVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final EmailVerificationService emailVerificationService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody final AuthenticationRequestDto authenticationRequestDto
    ) {
        return ResponseEntity.ok(
                authenticationService.authenticate(authenticationRequestDto));
    }

    @PostMapping("/email/resend-verification")
    public ResponseEntity<Void> resendVerificationLink(
            @RequestParam String email) {

        emailVerificationService.resendVerificationToken(email);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/email/verify")
    public ResponseEntity<UserProfileDto> verifyEmail(
            @RequestParam("uid") String userId, @RequestParam("t") String token) {

        final var verifiedUser = emailVerificationService.verifyEmail(UUID.fromString(userId), token);
        return ResponseEntity.ok(userMapper.toDto(verifiedUser));
    }
}