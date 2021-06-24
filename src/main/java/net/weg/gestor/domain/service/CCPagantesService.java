package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.assembler.CCPagantesAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.model.CCPagantesModel;
import net.weg.gestor.model.centrodecustoinput.CCPagantesInput;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private CentroDeCustoRepository centroDeCustoRepository;
    private ProjetoRepository projetoRepository;

    public List<CCPagantesModel> listartodos() {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());

    }

    public List<CCPagantesModel> listarporprojeto(Long projetoid) {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdProjeto(projetoid));

    }

    public CCPagantesModel cadastrar(CCPagantesInput ccPagantesInput) {
        if (ccPagantesInput.getTaxa() > 100 || ccPagantesInput.getTaxa() < 1) {
            throw new NegocioException("Insira uma taxa válida");
        }

        if (valorTotalTaxa(ccPagantesInput.getProjeto().getIdprojeto(), ccPagantesInput.getTaxa()) > 100) {
            throw new NegocioException("Esse valor ultrapassa o limite de 100");
        }

        CCPagantes ccPagantes = ccPagantesAssembler.toEntity(ccPagantesInput);

        boolean validation = centroDeCustoRepository.findById(
                ccPagantes.getCentrodecusto().getCodigo()).isPresent();

        if (!validation) {
            throw new NegocioException("ID De centro de custo inválido, tente novamente");
        }

        ccPagantes.setCentrodecusto(centroDeCustoRepository.findById2(ccPagantes.getCentrodecusto().getCodigo()));
        boolean validation2 = projetoRepository.findByIdProjeto2(ccPagantes.getProjeto().getIdprojeto()).isPresent();

        if (!validation2) {
            throw new NegocioException("ID Do Projeto inválido");
        }

        ccPagantes.setProjeto(projetoRepository.findByIdProjeto(ccPagantes.getProjeto().getIdprojeto()));
        ccPagantesRepository.save(ccPagantes);
        return ccPagantesAssembler.toModel(ccPagantes);
    }

    public int valorTotalTaxa(Long projetoid, int newtaxa) {
        List<CCPagantesModel> lista = listarporprojeto(projetoid);

        int soma= 0;
        for (int i = 0; i < lista.size(); ++i) {
            soma += lista.get(i).getTaxa();
        }

        return soma + newtaxa;
    }

}
