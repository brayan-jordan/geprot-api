package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.UsuarioAssembler;
import net.weg.gestor.api.model.consultor.ConsultorDTO;
import net.weg.gestor.api.model.consultor.ConsultorNaoAlocadoDTO;
import net.weg.gestor.api.model.input.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.input.ConsultorInputDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.FornecedorRepository;
import net.weg.gestor.domain.service.ConsultorService;
import net.weg.gestor.domain.service.ConsultoresAlocadosService;
import net.weg.gestor.domain.service.RoleUsuarioService;
import net.weg.gestor.domain.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultor")
@AllArgsConstructor
public class ConsultorController {
    /*  Nessa classe será controlado as rotas dos consultores e suas funções    */

    private ConsultoresAlocadosService consultoresAlocadosService;
    private UsuarioAssembler usuarioAssembler;
    private UsuarioService usuarioService;
    private RoleUsuarioService roleUsuarioService;
    private ConsultorAssembler consultorAssembler;
    private ConsultorService consultorService;
    private FornecedorRepository fornecedorRepository;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultorDTO cadastrar(@RequestBody ConsultorInputDTO consultor) {
        Usuario novoUsuario = usuarioAssembler.toEntity(consultor.getUsuario());
        RoleUsuarios novaRole = new RoleUsuarios();
        novoUsuario.setSenha(new BCryptPasswordEncoder().encode(consultor.getUsuario().getSenha()));
        Usuario usuarioCadastrado = usuarioService.cadastrar(novoUsuario);
        roleUsuarioService.cadastrar(usuarioCadastrado.getId());
        Consultor consultorCadastrar = consultorAssembler.toEntity(consultor);
        consultorCadastrar.setUsuario(usuarioCadastrado);
        consultorCadastrar.setFornecedor(fornecedorRepository.
                findByIdFornecedor(consultor.getFornecedor().getId()));
        consultorCadastrar = consultorService.cadastrar(consultorCadastrar);
        return consultorAssembler.toModel(consultorCadastrar);
    }

    @GetMapping("/buscar/{consultorId}")
    public ConsultorDTO buscarConsultorUnicoPorId(@PathVariable long consultorId){
        return consultorService.buscarConsultor(consultorId);
    }

    @GetMapping("/listar")
    public List<ConsultorNaoAlocadoDTO> buscarConsultores() {
        return consultorService.buscarTodosConsultores();
    }

    @GetMapping("/buscar/id/{pesquisarPorId}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorId(@PathVariable Long pesquisaPorId) {
        return consultorService.buscarConsultoresPorId(pesquisaPorId);
    }

    @GetMapping("/buscar/nome/{pesquisaPorNome}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNome(@PathVariable String pesquisaPorNome) {
        return consultorService.buscarConsultoresPorNome(pesquisaPorNome);
    }

    @GetMapping("/buscar/nomefornecedor/{pesquisaPorNomeFornecedor}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedor(@PathVariable String pesquisaPorNomeFornecedor) {
        return consultorService.buscarConsultoresPorNomeFornecedor(pesquisaPorNomeFornecedor);
    }

    @PostMapping("/alocar")
    public String alocarConsultor(@RequestBody AlocarConsultorInputDTO alocar) {
        return consultoresAlocadosService.alocarConsultor(alocar);
    }






}
