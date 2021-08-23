package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.LoginDTO;
import net.weg.gestor.domain.model.AuthenticationResponse;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.security.ImplementsUserDetailsService;
import net.weg.gestor.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private UsuarioAssembler usuarioAssembler;

    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDTO usuario) throws Exception {
        Usuario usuario1 = usuarioAssembler.toEntity(usuario);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario1.getUsername(), usuario1.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Usuario senha invalido", ex);
        }

        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(
                usuario1.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
