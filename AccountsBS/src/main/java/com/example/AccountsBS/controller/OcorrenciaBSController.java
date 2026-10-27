package com.example.AccountsBS.controller;

//Spring Framework
import com.example.AccountsBS.dto.response.MoradorBSResponseDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateOcorrenciaBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateOcorrenciaBSRequestDto;
import com.example.AccountsBS.dto.response.OcorrenciaBSResponseDto;
import com.example.AccountsBS.model.OcorrenciaBS;
import com.example.AccountsBS.service.OcorrenciaBSService;
import com.example.AccountsBS.mapper.OcorrenciaBSMapper;

//Dependencies
import java.net.URI;
import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Tag(name = "OcorrenciasBS", description = "Endpoints e operações relacionados ao gerenciamento de ocorrências no sistema BS")
@RestController
@RequestMapping("bairroSeguro/ocorrencias")
public class OcorrenciaBSController {

    private final OcorrenciaBSService ocorrenciaBSService;
    private final OcorrenciaBSMapper ocorrenciaBSMapper;

    public OcorrenciaBSController(OcorrenciaBSService ocorrenciaBSService, OcorrenciaBSMapper ocorrenciaBSMapper) {
        this.ocorrenciaBSService = ocorrenciaBSService;
        this.ocorrenciaBSMapper = ocorrenciaBSMapper;
    }

    @Operation(summary = "Cria uma nova ocorrência")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Ocorrência criada com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OcorrenciaBSResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados de cadastro inválidos",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateOcorrenciaBSRequestDto ocorrenciaDto) {
        OcorrenciaBS ocorrenciaBS = ocorrenciaBSMapper.fromCreateOcorrenciaBSRequestDtoToEntity(ocorrenciaDto);
        UUID uuid = ocorrenciaBSService.create(ocorrenciaBS);
        URI location = URI.create("/ocorrencias/" + uuid);
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Lista as ocorrências existentes")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Ocorrência encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<OcorrenciaBSResponseDto>> list() {
        List<OcorrenciaBS> ocorrencias = ocorrenciaBSService.findAll();
        List<OcorrenciaBSResponseDto> ocorrenciasResponseDtos = ocorrenciaBSMapper.fromOcorrenciaBSToDto(ocorrencias);
        return ResponseEntity.ok().body(ocorrenciasResponseDtos);
    }

    @Operation(summary = "Busca uma ocorrência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Morador encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoradorBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ocorrência não encontrada",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaBSResponseDto> findById(@PathVariable UUID id) {
        OcorrenciaBS ocorrenciaBS = ocorrenciaBSService.findById(id);
        return ResponseEntity.ok().body(ocorrenciaBSMapper.fromOcorrenciaBSToDto(ocorrenciaBS));
    }

    @Operation(summary = "Atualiza uma ocorrência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Ocorrência atualizada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ocorrência não encontrada",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateOcorrenciaBSRequestDto ocorrenciaDto) {
        OcorrenciaBS ocorrenciaBS = ocorrenciaBSMapper.fromUpdateOcorrenciaBSRequestDtoToEntity(ocorrenciaDto);
        OcorrenciaBS updated = ocorrenciaBSService.update(ocorrenciaBS, id);
        return ResponseEntity.ok().body(ocorrenciaBSMapper.fromOcorrenciaBSToDto(updated));
    }

    @Operation(summary = "Apaga uma ocorrência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Ocorrência apagada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OcorrenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Ocorrência não encontrada",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ocorrenciaBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
