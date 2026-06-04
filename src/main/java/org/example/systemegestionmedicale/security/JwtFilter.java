package org.example.systemegestionmedicale.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.systemegestionmedicale.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // prend l'autorisation de header
        String authHeader = request.getHeader("Authorization");

        // si y a pas de token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // sort de token
        String token = authHeader.substring(7);

        // prend username de  token
        String username = jwtUtil.extractUsername(token);

        // prend  user de  database
        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(username);

        //verification de tocken
        if (jwtUtil.validateToken(token, userDetails)) {

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            //  dit a Spring user connected
            SecurityContextHolder.getContext()
                    .setAuthentication(authToken);
        }

        // finir avec request
        filterChain.doFilter(request, response);
    }
}