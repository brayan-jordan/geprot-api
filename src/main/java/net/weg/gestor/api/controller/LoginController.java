package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.GestorAssembler;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.GestorDTO;
import net.weg.gestor.api.model.UsuarioLoginInputDTO;
import net.weg.gestor.domain.entities.AuthenticationResponse;
import net.weg.gestor.domain.entities.Gestor;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;
    private GestorRepository gestorRepository;
    private GestorAssembler gestorAssembler;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioLoginInputDTO usuario) throws Exception {
        Usuario usuario1 = usuarioAssembler.toEntityLogin(usuario);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario1.getUsername(), usuario1.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Usuario senha invalido", ex);
        }

        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(
                usuario1.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        usuario1 = usuarioRepository.findByEmail(usuario1.getEmail());
        GestorDTO gestor = gestorAssembler.toModel(gestorRepository.findByUsuarioId(usuario1.getId()));
        return ResponseEntity.ok(new AuthenticationResponse(jwt,gestor));
    }

}
