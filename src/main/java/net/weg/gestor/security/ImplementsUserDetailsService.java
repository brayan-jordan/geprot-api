package net.weg.gestor.security;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Repository
@Transactional
public class ImplementsUserDetailsService implements  UserDetailsService{

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null){
            throw new NegocioException("Usuario ou senha inválido");
        }
        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                true,
                true,
                true,
                true,
                usuario.getAuthorities()
        );

    }
}
