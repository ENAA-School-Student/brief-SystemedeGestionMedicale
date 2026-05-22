package org.example.systemegestionmedicale.service;

import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.DTO.AuthResponse;
import org.example.systemegestionmedicale.DTO.LoginRequest;
import org.example.systemegestionmedicale.DTO.RegisterRequest;
import org.example.systemegestionmedicale.Repository.MedecinRepository;
import org.example.systemegestionmedicale.Repository.PatientRepository;
import org.example.systemegestionmedicale.Repository.UserRepository;
import org.example.systemegestionmedicale.model.Medecin;
import org.example.systemegestionmedicale.model.Patient;
import org.example.systemegestionmedicale.model.Role;
import org.example.systemegestionmedicale.model.User;
import org.example.systemegestionmedicale.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user;

        if (request.getRole() == Role.MEDECIN) {
            user = Medecin.builder()
                    .username(request.getUsername())
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.MEDECIN)
                    .specialite("Généraliste") // Valeur par défaut, à améliorer si besoin
                    .build();
            medecinRepository.save((Medecin) user);
        } else if (request.getRole() == Role.PATIENT) {
            user = Patient.builder()
                    .username(request.getUsername())
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.PATIENT)
                    .prenom("Inconnu") // Valeur par défaut
                    .build();
            patientRepository.save((Patient) user);
        } else {
            user = User.builder()
                    .username(request.getUsername())
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                    )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
}
