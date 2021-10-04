package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ConsultorAssembler;
import net.weg.gestor.api.assembler.UsuarioAssembler;
import net.weg.gestor.api.model.ConsultorDTO;
import net.weg.gestor.api.model.input.ConsultorInputDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.FornecedorRepository;
import net.weg.gestor.domain.service.UsuarioService;
import net.weg.gestor.domain.service.RoleUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioAssembler usuarioAssembler;
    private RoleUsuarioService roleUsuarioService;
    private FornecedorRepository fornecedorRepository;
    private ConsultorAssembler consultorAssembler;


//    @GetMapping("/buscar/{usuarioId}")
//    public UsuarioDTO buscarUmUsuarioPorId(@PathVariable Long usuarioId) {
//        return usuarioService.buscar(usuarioId);
//    }

//    @DeleteMapping("/deletar/{usuarioId}")
//    public ResponseEntity<Usuario> remover(@PathVariable Long usuarioId) {
//        return usuarioService.excluir(usuarioId);
//    }

//    @PutMapping("/editar/{usuarioId}")
//    public UsuarioDTO editar(@Valid @PathVariable Long usuarioId, @RequestBody UsuarioEditarInputDTO usuario) {
//        return usuarioService.editar(usuarioId, usuario);
//    }

//    @GetMapping("/listar")
//    public List<UsuarioDTO> list2() {
//        return usuarioService.listartodos();
//    }



//    @PutMapping("/editar/admin/{usuarioId}")
//    public RoleUsuarioDTO editaPermissaoAdmin(@Valid @PathVariable long usuarioId){
//        return roleUsuarioService.editarPermissaoAdmin(usuarioId);
//    }
//
//    @PutMapping("/editar/user/{usuarioId}")
//    public RoleUsuarioDTO editaPermissaoUser(@Valid @PathVariable long usuarioId){
//        return roleUsuarioService.editarPermissaoUser(usuarioId);
//    }
//
//    @GetMapping("/listar/consultores")
//    public List<UsuarioConsultorDTO> buscarConsultores() {
//        return usuarioService.buscarConsultores();
//
//    }
//
//    @GetMapping("/buscar/consultor/{consultorId}")
//    public UsuarioConsultorDTO buscarConsultor(@PathVariable Long consultorId) {
//        return usuarioService.buscarConsultor(consultorId);
//    }

}