package net.weg.gestor.api.assembler;

import lombok.AllArgsConstructor;
import net.weg.gestor.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ConsultoresAlocadosAssembler {

    private ModelMapper modelMapper;
    private UsuarioRepository usuarioRepository;


}
