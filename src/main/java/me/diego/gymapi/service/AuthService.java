package me.diego.gymapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.diego.gymapi.config.security.JwtTokenService;
import me.diego.gymapi.config.security.WebSecurityConfig;
import me.diego.gymapi.dto.UserAuthModel;
import me.diego.gymapi.model.UserModel;
import me.diego.gymapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final WebSecurityConfig securityConfig;

    public String signInUser(UserAuthModel userAuthModel) {
        String username = userAuthModel.getUsername();
        UserModel userModel = userDetailsService.loadUserByUsername(username);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                userAuthModel.getPassword()));

        return jwtTokenService.generateToken(userModel);
    }

    @Transactional
    public String registerUser(UserAuthModel userAuthModel) {
        if (userRepository.existsByUsername(userAuthModel.getUsername())) {
            throw new UsernameNotFoundException("Username or email is already taken!");
        }

        UserModel user = UserModel.builder()
                .username(userAuthModel.getUsername())
                .password(securityConfig.getPasswordEncoder().encode(userAuthModel.getPassword()))
                .build();

        UserModel savedUser = userRepository.save(user);
        return jwtTokenService.generateToken(savedUser);
    }
}
