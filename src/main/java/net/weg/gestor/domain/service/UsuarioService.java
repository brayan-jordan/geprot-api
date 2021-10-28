package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.UsuarioAssembler;
import net.weg.gestor.api.model.input.GestorInputUpdate;
import net.weg.gestor.domain.entities.StatusUsuario;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.FornecedorRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private SecaoRepository secaoRepository;
    private UsuarioAssembler usuarioAssembler;
    private RoleUsuarioService roleUsuarioService;
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        usuario.setDataCadastro(LocalDate.now());
        usuario.setStatus(StatusUsuario.ATIVO);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario, GestorInputUpdate gestorInputUpdate) {
        usuario.setNome(gestorInputUpdate.getNomeNew());
        usuario.setSenha(new BCryptPasswordEncoder().encode(gestorInputUpdate.getSenhaNew()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarSenha(Usuario usuario, String newSenha){
        usuario.setSenha(new BCryptPasswordEncoder().encode(newSenha));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarNome(Usuario usuario, String newNome){
        usuario.setNome(newNome);
        return usuarioRepository.save(usuario);
    }


}
