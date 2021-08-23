package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.UsuarioDTO;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Usuario;
import net.weg.gestor.domain.repository.UsuarioRepository;
import net.weg.gestor.domain.repository.SecaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private SecaoRepository secaoRepository;
    private UsuarioAssembler usuarioAssembler;

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        boolean idSecaoValidation = secaoRepository.findById(usuario.getSecao().getId()).isPresent();
        if (!idSecaoValidation) {
            throw new NegocioException("ID Da seção é invalido, tente novamente");
        }

        usuario.setSecao(secaoRepository.findById2(usuario.getSecao().getId()));

        boolean idValidation = usuarioRepository.findByidgestor(usuario.getId()).isPresent();
        if (idValidation) {
            throw new NegocioException("Já existe um gestor com esse ID");
        }

        if (usuario.getId() == 0) {
            throw new NegocioException("ID Inválido");
        }

        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<Usuario> excluir(Long gestorId) {
        if(!usuarioRepository.existsById(gestorId)) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(gestorId);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Usuario> editar(Long gestorId, Usuario usuario) {
        if(!usuarioRepository.existsById(gestorId)) {
            throw new NegocioException("Nao existe um gestor com esse ID para ser editado");
        }

        usuario.setId(gestorId);
        usuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Usuario> buscar(Long gestorId) {
        return usuarioRepository.findById(gestorId).map(gestor -> ResponseEntity.ok(gestor))
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Usuario> listartodos() {
        return usuarioRepository.findAll();
    }

    public List<UsuarioDTO> list2() {
        return usuarioAssembler.toCollectionModel(usuarioRepository.findAll());

    }


}
