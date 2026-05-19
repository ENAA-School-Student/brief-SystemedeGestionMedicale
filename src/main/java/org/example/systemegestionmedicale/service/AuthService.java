package org.example.systemegestionmedicale.service;




import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.AuthResponse;
import org.example.systemegestionmedicale.DTO.LoginRequest;
import org.example.systemegestionmedicale.DTO.RegisterRequest;
import org.example.systemegestionmedicale.Repository.UserRepository;
import org.example.systemegestionmedicale.model.Role;
import org.example.systemegestionmedicale.model.User;
import org.example.systemegestionmedicale.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String token =
                jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token =
                jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }
}
