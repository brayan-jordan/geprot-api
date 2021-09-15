package net.weg.gestor.domain.service;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.exception.NegocioException;
import net.weg.gestor.domain.model.StatusProjeto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationsService {

    public StatusProjeto returnTypeStatus(int typeStatus) {
        switch (typeStatus) {
            case 1:
                return StatusProjeto.NAO_INICIADO;
            case 2:
                return StatusProjeto.ATRASADO;
            case 3:
                return StatusProjeto.CONCLUIDO;
            case 4:
                return StatusProjeto.EM_ANDAMENTO;
            default:
                throw new NegocioException("Numero invalido");
        }
    }

}
