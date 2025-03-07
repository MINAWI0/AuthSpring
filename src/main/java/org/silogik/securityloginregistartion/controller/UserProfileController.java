package org.silogik.securityloginregistartion.controller;

import lombok.RequiredArgsConstructor;
import org.silogik.securityloginregistartion.dto.UserProfileDto;
import org.silogik.securityloginregistartion.mapper.UserMapper;
import org.silogik.securityloginregistartion.service.EmailVerificationService;
import org.silogik.securityloginregistartion.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUserProfile(
            final Authentication authentication) {

        final var user =
                userService.getUserByUsername(authentication.getName());

        return ResponseEntity.ok(userMapper.toDto(user));
    }

}