package net.weg.gestor.api.controller;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.map.ConsultorAssembler;
import net.weg.gestor.api.map.UsuarioAssembler;
import net.weg.gestor.api.model.ConsultorAlocadoNoProjetoDTO;
import net.weg.gestor.api.model.consultor.ConsultorDTO;
import net.weg.gestor.api.model.consultor.ConsultorNaoAlocadoDTO;
import net.weg.gestor.api.model.input.AlocarConsultorInputDTO;
import net.weg.gestor.api.model.input.ConsultorInputDTO;
import net.weg.gestor.api.model.input.SkillDTO;
import net.weg.gestor.domain.entities.Consultor;
import net.weg.gestor.domain.entities.RoleUsuarios;
import net.weg.gestor.domain.entities.Skill;
import net.weg.gestor.domain.entities.Usuario;
import net.weg.gestor.domain.repository.FornecedorRepository;
import net.weg.gestor.domain.service.*;
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
    private SkillService skillService;

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
        consultorCadastrar.setSkills(skillService.buscarSkillsSelecionadas(consultor.getSkills()));
        consultorCadastrar = consultorService.cadastrar(consultorCadastrar);
        return consultorAssembler.toModel(consultorCadastrar);
    }

    @GetMapping("/porprojeto/{projetoId}")
    public List<ConsultorAlocadoNoProjetoDTO> consultoresDoProjeto(@PathVariable Long projetoId) {
        return consultorService.buscarConsultorePorProjeto(projetoId);
    }

    @GetMapping("/buscar/{consultorId}")
    public ConsultorDTO buscarConsultorUnicoPorId(@PathVariable long consultorId){
        return consultorService.buscarConsultor(consultorId);
    }

    @GetMapping("/listarporskill/{skillId}")
    public List<ConsultorDTO> buscarConsultorPorSkill(@PathVariable Long skillId) {
        return consultorService.buscarConsultoresPorSkill(skillId);
    }

    @GetMapping("/listar")
    public List<ConsultorNaoAlocadoDTO> buscarConsultores() {
        return consultorService.buscarTodosConsultores();
    }

    @GetMapping("/buscar/id/{id}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorId(@PathVariable Long id) {
        return consultorService.buscarConsultoresPorId(id);
    }

    @GetMapping("/buscar/nome/{nome}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNome(@PathVariable String nome) {
        return consultorService.buscarConsultoresPorNome(nome);
    }

    @GetMapping("/buscar/nomefornecedor/{nomefornecedor}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedor(@PathVariable String nomefornecedor) {
        return consultorService.buscarConsultoresPorNomeFornecedor(nomefornecedor);
    }

    @GetMapping("/buscar/nome/nomefornecedor/{nome}/{nomefornecedor}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeENomeFornecedor(
            @PathVariable String nome,
            @PathVariable String nomeFornecedor)
    {
        return consultorService.buscarConsultoresPorNomeFornecedorENome(nome, nomeFornecedor);
    }

    @GetMapping("/buscar/nome/id/{nome}/{id}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeEId(
            @PathVariable String nome,
            @PathVariable Long id)
    {
        return consultorService.buscarConsultoresPorIdeNome(id, nome);
    }

    @GetMapping("/buscar/nomefornecedor/id/{nomefornecedor}/{id}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedorEId(
            @PathVariable String nomefornecedor,
            @PathVariable Long id)
    {
        return consultorService.buscarConsultoresPorIdeNomeFornecedor(id, nomefornecedor);
    }

    @GetMapping("/buscar/nomefornecedor/id/nome/{nomefornecedor}/{id}/{nome}")
    public List<ConsultorNaoAlocadoDTO> buscarConsultoresPorNomeFornecedorEIdENome(
            @PathVariable String nomefornecedor,
            @PathVariable Long id,
            @PathVariable String nome)
    {
        return consultorService.buscarConsultoresPorNomeFornecedorENomeEId(nome, nomefornecedor, id);
    }

    @PostMapping("/alocar")
    public String alocarConsultor(@RequestBody AlocarConsultorInputDTO alocar) {
        return consultoresAlocadosService.alocarConsultor(alocar);
    }

    @GetMapping("/skills")
    public List<SkillDTO> listarSkills(){
        return skillService.listarSkills();
    }

    @GetMapping("/skills/{consultorId}")
    public List<SkillDTO> buscarSkillsConsultor(@PathVariable Long consultorId) {
        return skillService.listarSkillsConsultor(consultorId);
    }

    @GetMapping("/pegaskill/{skillId}")
    public SkillDTO buscarSkillPeloId(@PathVariable Long skillId) {
        return skillService.buscarSkillPeloId(skillId);
    }




}
