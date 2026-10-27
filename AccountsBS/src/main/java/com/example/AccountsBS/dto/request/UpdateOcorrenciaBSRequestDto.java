package com.example.AccountsBS.dto.request;

//AccountsBS
import com.example.AccountsBS.model.Tag;

//Dependencies
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para atualizar uma ocorrência")
public record UpdateOcorrenciaBSRequestDto(

    @Schema(description = "Título da ocorrência", example = "Poste consertado")
    @NotBlank(message = "O título é obrigatório")
    String tituloOcorrencia,

    @Schema(description = "Descrição da ocorrência", example = "O poste da rua x foi consertado")
    @NotBlank(message = "A descrição é obrigatória")@Size(message = "Tamanho máximo de 2000 caracteres")
    String descricao,

    @Schema(description = "Tag da ocorrência", example = "Infraestrutural")
    @NotNull(message = "A tag é obrigatória")
    Tag tag) {
}
