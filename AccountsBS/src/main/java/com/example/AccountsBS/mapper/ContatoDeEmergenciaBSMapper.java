package com.example.AccountsBS.mapper;

//Spring Framework
import org.springframework.stereotype.Component;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateContatoDeEmergenciaBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateContatoDeEmergenciaBSRequestDto;
import com.example.AccountsBS.dto.response.ContatoDeEmergenciaBSResponseDto;
import com.example.AccountsBS.model.ContatoDeEmergenciaBS;

//Dependencies
import java.util.List;

@Component
public class ContatoDeEmergenciaBSMapper {

    public ContatoDeEmergenciaBS fromCreateContatoDeEmergenciaBSRequestDtoToEntity(CreateContatoDeEmergenciaBSRequestDto contatoDeEmergenciaDto) {
        return new ContatoDeEmergenciaBS(
            contatoDeEmergenciaDto.nomeCompleto(),
            contatoDeEmergenciaDto.telefone(),
            contatoDeEmergenciaDto.descricao()
        );
    }

    public ContatoDeEmergenciaBS fromUpdateContatoDeEmergenciaBSRequestDtoToEntity(UpdateContatoDeEmergenciaBSRequestDto contatoDeEmergenciaDto) {
        return new ContatoDeEmergenciaBS(
                contatoDeEmergenciaDto.nomeCompleto(),
                contatoDeEmergenciaDto.telefone(),
                contatoDeEmergenciaDto.descricao()
        );
    }

    public ContatoDeEmergenciaBSResponseDto fromContatoDeEmergenciaBSToDto(ContatoDeEmergenciaBS contatoDeEmergenciaBS) {
        return new ContatoDeEmergenciaBSResponseDto(
            contatoDeEmergenciaBS.getId(),
            contatoDeEmergenciaBS.getNomeCompleto(),
            contatoDeEmergenciaBS.getTelefone(),
            contatoDeEmergenciaBS.getDescricao()
        );
    }

    public List<ContatoDeEmergenciaBSResponseDto> fromContatoDeEmergenciaBSToDto(List<ContatoDeEmergenciaBS> contatoDeEmergenciaBS) {
        return contatoDeEmergenciaBS.stream().map(this::fromContatoDeEmergenciaBSToDto).toList();
    }
}
