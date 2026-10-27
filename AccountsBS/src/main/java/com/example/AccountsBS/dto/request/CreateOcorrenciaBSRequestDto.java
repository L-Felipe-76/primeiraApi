package com.example.AccountsBS.dto.request;

//AccountsBS

import com.example.AccountsBS.model.Tag;

//Dependencies
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados necessários para criar uma ocorrência")
public record CreateOcorrenciaBSRequestDto(

    @Schema(description = "Título da ocorrência", example = "Poste Explodiu")
    @NotBlank(message = "O título é obrigatório")
    String tituloOcorrencia,

    @Schema(description = "Descrição da ocorrência", example = "O poste da rua x sofreu um curto circuito nos disjuntores que causou uma explosão em alguma peças dele")
    @NotBlank(message = "A descrição é obrigatória")@Size(message = "Tamanho máximo de 2000 caracteres")
    String descricao,

    @Schema(description = "Tag da ocorrência", example = "Infraestrutural")
    @NotNull(message = "A tag é obrigatória")
    Tag tag) {
}
