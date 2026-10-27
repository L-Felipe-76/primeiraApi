package com.example.AccountsBS.mapper;

//Spring Framework
import org.springframework.stereotype.Component;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateOcorrenciaBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateOcorrenciaBSRequestDto;
import com.example.AccountsBS.dto.response.OcorrenciaBSResponseDto;
import com.example.AccountsBS.model.OcorrenciaBS;

//Dependencies
import java.util.List;

@Component
public class OcorrenciaBSMapper {

    public OcorrenciaBS fromCreateOcorrenciaBSRequestDtoToEntity(CreateOcorrenciaBSRequestDto ocorrenciaDto) {
        return new OcorrenciaBS(
            ocorrenciaDto.tituloOcorrencia(),
            ocorrenciaDto.descricao(),
            ocorrenciaDto.tag()
        );
    }

    public OcorrenciaBS fromUpdateOcorrenciaBSRequestDtoToEntity(UpdateOcorrenciaBSRequestDto ocorrenciaDto) {
        return new OcorrenciaBS(
            ocorrenciaDto.tituloOcorrencia(),
            ocorrenciaDto.descricao(),
            ocorrenciaDto.tag()
        );
    }

    public OcorrenciaBSResponseDto fromOcorrenciaBSToDto(OcorrenciaBS ocorrenciaBS) {
        return new OcorrenciaBSResponseDto(
            ocorrenciaBS.getId(),
            ocorrenciaBS.getTituloOcorrencia(),
            ocorrenciaBS.getDescricao(),
            ocorrenciaBS.getTag(),
            ocorrenciaBS.getDataHora()
        );
    }

    public List<OcorrenciaBSResponseDto> fromOcorrenciaBSToDto(List<OcorrenciaBS> ocorrencias) {
        return ocorrencias.stream().map(this::fromOcorrenciaBSToDto).toList();
    }
}
