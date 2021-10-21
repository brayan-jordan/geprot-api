package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.RoleUsuarioAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.repository.RoleUsuarioRepository;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RoleUsuarioService {
    /*  Nessa classe será aplicado as funções das Permissões dos usuarios    */

    private RoleUsuarioRepository roleUsuarioRepository;
    private RoleUsuarioAssembler roleUsuarioAssembler;
    private UsuarioRepository usuarioRepository;

    @Transactional
    public RoleUsuarios cadastrar(long idConsultor){
        RoleUsuarios roleUsuarios = new RoleUsuarios();
        roleUsuarios.setUsuarios_id(idConsultor);
        roleUsuarios.setRole_nome("ROLE_CONSULTOR");
        return roleUsuarioRepository.save(roleUsuarios);
    }

    public RoleUsuarios buscar(Long roleId){
        return roleUsuarioRepository.findById(roleId).orElseThrow(() -> new NegocioException("Role não encontrada."));
    }

    @Transactional
    public void deletar(Long roleId){
        roleUsuarioRepository.deleteById(roleId);
    }

}
