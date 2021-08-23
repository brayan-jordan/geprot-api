package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.ProjetoAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.api.model.ProjetoDTO;
import net.weg.gestor.api.model.projetoinputDTO.ProjetoInput;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {

    private ProjetoRepository projetoRepository;
    private GestorRepository gestorRepository;
    private ProjetoAssembler projetoAssembler;

    public List<Projeto> listartodos() {
        return projetoRepository.findAll();
    }

    public List<Projeto> listarStatus(StatusProjeto statusprojeto){
        return projetoRepository.findByStatusProjeto(statusprojeto);

    }

    public ProjetoDTO cadastrar(ProjetoInput projeto){
        boolean gestorVerification = gestorRepository.findById(projeto.getGestor().getIdgestor()).isPresent();
        if(!gestorVerification){
            throw new NegocioException("Não existe um gestor com esse ID ");
        }

        Projeto projeto1 = projetoAssembler.toEntity(projeto);

        projeto1.setDatainicio(LocalDateTime.now());
        projeto1.setHorastrabalhadas(0);
        projeto1.setValorutilizado(0);
        projeto1.setValorrestante(projeto.getValorprojeto());
        projeto1.setStatusprojeto(StatusProjeto.EM_ANDAMENTO);
        projeto1.setUsuario(gestorRepository.findByidgestor2(projeto1.getUsuario().getId()));
        projetoRepository.save(projeto1);

        return projetoAssembler.toModel(projeto1);

    }

    public Projeto editar(Long idDoProjeto, Projeto projeto){
        boolean projetoVerification = projetoRepository.findById(idDoProjeto).isPresent();
        if(!projetoVerification){
            throw new NegocioException("Não existe um projeto com esse ID ");
        }
        projeto.setId(idDoProjeto);
        return projetoRepository.save(projeto);
    }
    public Projeto editarAtrasado(Long idDoProjeto){
        boolean projetoVerification = projetoRepository.findById(idDoProjeto).isPresent();
        if(!projetoVerification){
            throw new NegocioException("Não existe um projeto com esse ID ");

        }

        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);

        projeto.setStatusprojeto(StatusProjeto.ATRASADO);
        projetoRepository.save(projeto);
        return projeto;
    }

    public Projeto editarConcluida(Long idDoProjeto){
        boolean projetoVerification = projetoRepository.findById(idDoProjeto).isPresent();
        if(!projetoVerification){
            throw new NegocioException("Não existe um projeto com esse ID ");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);

        projeto.setStatusprojeto(StatusProjeto.CONCLUIDO);
        projeto.setDatafinalizacao(LocalDateTime.now());
        return projetoRepository.save(projeto);
    }

    public Projeto editarAndamento(Long idDoProjeto){
        boolean projetoVerification = projetoRepository.findById(idDoProjeto).isPresent();
        if(!projetoVerification){
            throw new NegocioException("Não existe um projeto com esse ID ");
        }
        Projeto projeto = projetoRepository.findByIdProjeto(idDoProjeto);

        projeto.setStatusprojeto(StatusProjeto.EM_ANDAMENTO);
        projeto.setDatafinalizacao(LocalDateTime.now());
        return projetoRepository.save(projeto);
    }

}
