package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.api.model.input.FornecedorInputDTO;
import net.weg.gestor.api.model.input.UsuarioInputDTO;
import net.weg.gestor.domain.entities.Fornecedor;
import net.weg.gestor.domain.entities.Usuario;
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
