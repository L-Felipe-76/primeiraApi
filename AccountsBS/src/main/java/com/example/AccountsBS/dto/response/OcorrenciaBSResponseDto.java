package com.example.AccountsBS.dto.response;

//AccountsBS
import com.example.AccountsBS.model.Tag;

//Dependencies
import java.time.LocalDateTime;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

public record OcorrenciaBSResponseDto(

    @Schema(description = "Id único do usuário")
    UUID id,

    @Schema(description = "Título da ocorrência", example = "Poste Explodiu")
    String tituloOcorrencia,

    @Schema(description = "Descrição da ocorrência", example = "O poste da rua x sofreu um curto circuito nos disjuntores que causou uma explosão em alguma peças dele")
    String descricao,

    @Schema(description = "Tag da ocorrência", example = "Infraestrutural")
    Tag tag,

    @Schema(description = "Data e hora do cadastro da ocorrência", example = "2026-10-26T17:56:00")
    LocalDateTime dataHora ) {
}
