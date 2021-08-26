package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.assembler.CCPagantesAssembler;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.CCPagantes;
import net.weg.gestor.domain.repository.CCPagantesRepository;
import net.weg.gestor.domain.repository.CentroDeCustoRepository;
import net.weg.gestor.domain.repository.ProjetoRepository;
import net.weg.gestor.api.model.CCPagantesDTO;
import net.weg.gestor.api.model.centrodecustoinputDTO.CCPagantesInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CCPagantesService {

    private CCPagantesAssembler ccPagantesAssembler;
    private CCPagantesRepository ccPagantesRepository;
    private CentroDeCustoRepository centroDeCustoRepository;
    private ProjetoRepository projetoRepository;

    public List<CCPagantesDTO> listartodos() {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findAll());

    }

    public List<CCPagantesDTO> listarporprojeto(Long projetoid) {
        return ccPagantesAssembler.toCollectionModel(ccPagantesRepository.findByIdProjeto(projetoid));

    }

    public List<CCPagantesDTO> cadastrar(List<CCPagantesInputDTO> ccPagantesInputDTO) {
        if (calcularTaxa(ccPagantesInputDTO) != 100) {
            throw new NegocioException("Verifique os valores da taxa (Não é igual a 100)");
        }

        List<CCPagantes> ccPagantes = ccPagantesAssembler.toCollectionEntity(ccPagantesInputDTO);

        boolean validation = centroDeCustoRepository.findById(
                ccPagantes.get(0).getCentrodecusto().getId()).isPresent();

        if (!validation) {
            throw new NegocioException("Verifique os centros de custos informados (ID Nao encontrado)");
        }

        for (int i = 0; i < ccPagantes.size(); ++i) {
            ccPagantes.get(i).setCentrodecusto(centroDeCustoRepository.findById2
                    (ccPagantes.get(i).getCentrodecusto().getId()));
        }

        boolean validation2 = projetoRepository.findByIdProjeto2(ccPagantes.get(0).getProjeto().getId()).isPresent();

        if (!validation2) {
            throw new NegocioException("Verifique o projeto informado (ID Nao encontrado)");
        }

        for (int i = 0; i < ccPagantes.size(); ++i) {
            ccPagantes.get(i).setProjeto(projetoRepository.findByIdProjeto(
                    ccPagantes.get(i).getProjeto().getId()));
        }

       for (int i = 0; i < ccPagantes.size(); ++i) {
           ccPagantesRepository.save(ccPagantes.get(i));
       }

        return ccPagantesAssembler.toCollectionModel(ccPagantes);
    }

    public int calcularTaxa(List<CCPagantesInputDTO> lista) {
        int soma = 0;
        for (int i = 0; i < lista.size(); ++i) {
            soma += lista.get(i).getTaxa();
        }
        return soma;

    }

}
