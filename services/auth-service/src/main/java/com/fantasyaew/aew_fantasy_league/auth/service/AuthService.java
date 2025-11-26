package com.fantasyaew.aew_fantasy_league.auth.service;

import com.fantasyaew.aew_fantasy_league.auth.dto.AuthResponse;
import com.fantasyaew.aew_fantasy_league.auth.dto.LoginRequest;
import com.fantasyaew.aew_fantasy_league.auth.dto.RegisterRequest;
import com.fantasyaew.aew_fantasy_league.auth.entity.Role;
import com.fantasyaew.aew_fantasy_league.auth.entity.User;
import com.fantasyaew.aew_fantasy_league.auth.repository.RoleRepository;
import com.fantasyaew.aew_fantasy_league.auth.repository.UserRepository;
import com.fantasyaew.aew_fantasy_league.auth.security.CustomUserDetailsService;
import com.fantasyaew.aew_fantasy_league.auth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create new user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .isEnabled(true)
                .isAccountNonLocked(true)
                .build();

        // Assign default USER role
        Role userRole = roleRepository.findByName(Role.RoleType.USER)
                .orElseThrow(() -> new RuntimeException("User role not found. Please initialize roles."));
        user.setRoles(new HashSet<>(Set.of(userRole)));

        user = userRepository.save(user);

        // Generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return buildAuthResponse(user, token);
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseGet(() -> userRepository.findByEmail(userDetails.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found")));

        String token = jwtTokenUtil.generateToken(userDetails);

        return buildAuthResponse(user, token);
    }

    public AuthResponse refreshToken(String token) {
        if (jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            User user = userRepository.findByUsername(username)
                    .orElseGet(() -> userRepository.findByEmail(username)
                            .orElseThrow(() -> new RuntimeException("User not found")));

            String newToken = jwtTokenUtil.generateToken(userDetails);
            return buildAuthResponse(user, newToken);
        } else {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    private AuthResponse buildAuthResponse(User user, String token) {
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(roles)
                .build();
    }
}
