package br.com.fiap.cp1.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.cp1.security.JwtTokenUtil;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService,
                                  JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Buscar detalhes do usuário
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            // Gerar token JWT
            String token = jwtTokenUtil.generateToken(userDetails);

            // Retornar resposta de sucesso com o token JWT
            LoginResponse response = new LoginResponse("Usuário autenticado com sucesso!", token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // Tratar erros de autenticação e retornar uma resposta adequada
            LoginResponse response = new LoginResponse("Falha na autenticação: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // DTO para requisição de login
    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    // DTO para resposta de login
    @Getter
    @Setter
    public static class LoginResponse {
        private String message;
        private String token;

        public LoginResponse(String message, String token) {
            this.message = message;
            this.token = token;
        }
    }
}

