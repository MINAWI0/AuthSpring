package org.silogik.securityloginregistartion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.silogik.securityloginregistartion.dto.RegistrationRequestDto;
import org.silogik.securityloginregistartion.dto.RegistrationResponseDto;
import org.silogik.securityloginregistartion.mapper.UserRegistrationMapper;
import org.silogik.securityloginregistartion.service.EmailVerificationService;
import org.silogik.securityloginregistartion.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;
    private final EmailVerificationService emailVerificationService;
    @Value("${email-verification.required}")
    private boolean emailVerificationRequired;

    private final UserRegistrationMapper userRegistrationMapper;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> registerUser(
            @Valid @RequestBody final RegistrationRequestDto registrationDTO) {

        final var registeredUser = userRegistrationService.registerUser(
                userRegistrationMapper.toEntity(registrationDTO)
        );

        if (emailVerificationRequired) {
            emailVerificationService.sendVerificationToken(
                    registeredUser.getId(), registeredUser.getEmail());
        }
        return ResponseEntity.ok(
                userRegistrationMapper.toRegistrationResponseDto(
                        registeredUser));
    }
}
