package com.example.AccountsBS.mapper;

//Spring Framework
import org.springframework.stereotype.Component;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateRedeDeApoioBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateRedeDeApoioBSRequestDto;
import com.example.AccountsBS.dto.response.RedeDeApoioBSResponseDto;
import com.example.AccountsBS.model.RedeDeApoioBS;

//Dependencies
import java.util.List;

@Component
public class RedeDeApoioBSMapper {

    public RedeDeApoioBS fromCreateRedeDeApoioBSRequestDtoToEntity(CreateRedeDeApoioBSRequestDto redeDeApoioDto) {
        return new RedeDeApoioBS(
            redeDeApoioDto.cep(),
            redeDeApoioDto.estado(),
            redeDeApoioDto.cidade(),
            redeDeApoioDto.bairro(),
            redeDeApoioDto.integrantes()
        );
    }

    public RedeDeApoioBS fromUpdateRedeDeApoioBSRequestDtoToEntity(UpdateRedeDeApoioBSRequestDto redeDeApoioDto) {
        return new RedeDeApoioBS(
            redeDeApoioDto.cep(),
            redeDeApoioDto.estado(),
            redeDeApoioDto.cidade(),
            redeDeApoioDto.bairro(),
            redeDeApoioDto.integrantes()
        );
    }

    public RedeDeApoioBSResponseDto fromRedeDeApoioBSToDto(RedeDeApoioBS redeDeApoioBS) {
        return new RedeDeApoioBSResponseDto(
            redeDeApoioBS.getId(),
            redeDeApoioBS.getCep(),
            redeDeApoioBS.getEstado(),
            redeDeApoioBS.getCidade(),
            redeDeApoioBS.getBairro(),
            redeDeApoioBS.getIntegrantes()
        );
    }

    public List<RedeDeApoioBSResponseDto> fromRedeDeApoioBSToDto(List<RedeDeApoioBS> redeDeApoioBS) {
        return redeDeApoioBS.stream().map(this::fromRedeDeApoioBSToDto).toList();
    }
}
