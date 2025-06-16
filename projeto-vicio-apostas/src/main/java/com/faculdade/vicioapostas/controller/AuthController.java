package com.faculdade.vicioapostas.controller;

import com.faculdade.vicioapostas.model.Usuario;
import com.faculdade.vicioapostas.security.JwtUtil;
import com.faculdade.vicioapostas.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticação", description = "Endpoints para autenticação e registro de usuários")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica um usuário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
            );
            
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            Usuario usuario = usuarioService.buscarPorEmail(loginRequest.getEmail()).orElse(null);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", usuario);
            response.put("message", "Login realizado com sucesso");
            
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    
    @PostMapping("/register")
    @Operation(summary = "Registrar usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já cadastrado",
                content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<Map<String, Object>> register(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.salvar(usuario);
            String token = jwtUtil.generateToken(novoUsuario.getEmail());
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", novoUsuario);
            response.put("message", "Usuário cadastrado com sucesso");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    // Classe interna para o request de login
    public static class LoginRequest {
        private String email;
        private String senha;
        
        public String getEmail() {
            return email;
        }
        
        public void setEmail(String email) {
            this.email = email;
        }
        
        public String getSenha() {
            return senha;
        }
        
        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}

