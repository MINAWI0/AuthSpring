package org.silogik.securityloginregistartion.service.sec;

import lombok.RequiredArgsConstructor;
import org.silogik.securityloginregistartion.exception.EmailVerificationException;
import org.silogik.securityloginregistartion.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Value("${email-verification.required}")
    private boolean emailVerificationRequired;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return userRepository.findByUsername(username).map(user -> {
            if (emailVerificationRequired && !user.isEmailVerified()) {
                throw new EmailVerificationException(
                        "Your email is not verified");
            }
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        }).orElseThrow(() -> new UsernameNotFoundException(
                "User with username [%s] not found".formatted(username)));
    }
}
