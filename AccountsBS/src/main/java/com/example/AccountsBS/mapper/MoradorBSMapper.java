package com.example.AccountsBS.mapper;

//Spring Framework
import org.springframework.stereotype.Component;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateMoradorBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateMoradorBSRequestDto;
import com.example.AccountsBS.dto.response.MoradorBSResponseDto;
import com.example.AccountsBS.model.MoradorBS;

//Dependencies
import java.util.List;

@Component
public class MoradorBSMapper {

    public MoradorBS fromCreateMoradorBSRequestDtoToEntity(CreateMoradorBSRequestDto moradorDto) {
        return new MoradorBS(
            moradorDto.nomeCompleto(),
            moradorDto.genero(),
            moradorDto.dataDeNascimento(),
            moradorDto.telefone()
        );
    }

    public MoradorBS fromUpdateMoradorBSRequestDtoToEntity(UpdateMoradorBSRequestDto moradorDto) {
        return new MoradorBS(
            moradorDto.nomeCompleto(),
            moradorDto.genero(),
            moradorDto.dataDeNascimento(),
            moradorDto.telefone()
        );
    }

    public MoradorBSResponseDto fromMoradorBSToDto(MoradorBS moradorBS) {
        return new MoradorBSResponseDto(
            moradorBS.getId(),
            moradorBS.getNomeCompleto(),
            moradorBS.getGenero(),
            moradorBS.getDataDeNascimento(),
            moradorBS.getTelefone()
        );
    }

    public List<MoradorBSResponseDto> fromMoradorBSToDto(List<MoradorBS> moradores) {
        return moradores.stream().map(this::fromMoradorBSToDto).toList();
    }
}
