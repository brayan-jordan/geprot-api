package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioEditarInputDTO;
import net.weg.gestor.api.model.usuarioinputDTO.UsuarioInputDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private SecaoRepository secaoRepository;
    private UsuarioAssembler usuarioAssembler;
    private RoleUsuarioService roleUsuarioService;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        boolean idSecaoValidation = secaoRepository.findById(usuario.getSecao().getId()).isPresent();
        if (!idSecaoValidation) {
            throw new NegocioException("ID Da seção é invalido, tente novamente");
        }
        usuario.setSecao(secaoRepository.findById2(usuario.getSecao().getId()));
        boolean idValidation = usuarioRepository.findByIdUsuario(usuario.getId()).isPresent();
        if (idValidation) {
            throw new NegocioException("Já existe um gestor com esse ID");
        }
        if (usuario.getId() == 0) {
            throw new NegocioException("ID Inválido");
        }
        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<Usuario> excluir(Long usuarioId) {
        if(!usuarioRepository.existsById(usuarioId)) {
            return ResponseEntity.notFound().build();
        }
        roleUsuarioService.deletarPorIdUsuario(usuarioId);
        usuarioRepository.deleteById(usuarioId);
        return ResponseEntity.noContent().build();
    }

    public Usuario editar(Long usuarioId, UsuarioEditarInputDTO usuario) {
        if(!usuarioRepository.existsById(usuarioId)) {
            throw new NegocioException("Nao existe um gestor com esse ID para ser editado");
        }
        Usuario usuario1 = usuarioRepository.findByIdUsuario2(usuarioId);
        usuario1.setNome(usuario.getNome());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuario1 = usuarioRepository.save(usuario1);
        return usuario1;
    }

    public ResponseEntity<Usuario> buscar(Long usuarioId) {
        return usuarioRepository.findByIdUsuario(usuarioId).map(gestor -> ResponseEntity.ok(gestor))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<UsuarioDTO> listartodos() {
        return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());
    }
}
