package net.weg.gestor.api.map;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.input.FornecedorInputDTO;
import net.weg.gestor.domain.entities.Fornecedor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FornecedorAssembler {

    private ModelMapper modelMapper;

    public Fornecedor toEntity(FornecedorInputDTO fornecedorInputDTO) {
        return modelMapper.map(fornecedorInputDTO, Fornecedor.class);

    }

}
