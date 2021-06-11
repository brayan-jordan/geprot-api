package net.weg.gestor.domain.service;


import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.Projeto;
import net.weg.gestor.domain.model.StatusProjeto;
import net.weg.gestor.domain.repository.GestorRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoService {
    private ProjetoRepository projetoRepository;
    private GestorRepository gestorRepository;

    public List<Projeto> listartodos() {
        return projetoRepository.findAll();
    }

    public Projeto cadastrar(Projeto projeto){
        boolean gestorVerification = gestorRepository.findById(projeto.getGestorid()).isPresent();
        if(!gestorVerification){
            throw new NegocioException("Não existe um gestor com esse ID ");
        }

        projeto.setHorastrabalhadas(0);
        projeto.setDatainicio(LocalDateTime.now());
        projeto.setHorastrabalhadas(0);
        projeto.setValorutilizado(0);
        projeto.setValorrestante(projeto.getValorprojeto() - projeto.getValorutilizado());
        projeto.setStatusprojeto(StatusProjeto.EM_ANDAMENTO);
        return projetoRepository.save(projeto);

    }

    public Projeto editar(Long idDoProjeto, Projeto projeto){
        boolean projetoVerification = projetoRepository.findById(idDoProjeto).isPresent();
        if(!projetoVerification){
            throw new NegocioException("Não existe um projeto com esse ID ");
        }
        projeto.setIdprojeto(idDoProjeto);
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

}
