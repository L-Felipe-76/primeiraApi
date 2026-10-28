package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateContatoDeEmergenciaBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateContatoDeEmergenciaBSRequestDto;
import com.example.AccountsBS.dto.response.ContatoDeEmergenciaBSResponseDto;
import com.example.AccountsBS.model.ContatoDeEmergenciaBS;
import com.example.AccountsBS.service.ContatoDeEmergenciaBSService;
import com.example.AccountsBS.mapper.ContatoDeEmergenciaBSMapper;

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

@Tag(name = "ContatosDeEmergenciaBS", description = "Endpoints e operações relacionados ao gerenciamento de contatos de emergência no sistema BS")
@RestController
@RequestMapping("bairroSeguro/contatosDeEmergencia")
public class ContatoDeEmergenciaBSController {

    private final ContatoDeEmergenciaBSService contatoDeEmergenciaBSService;
    private final ContatoDeEmergenciaBSMapper contatoDeEmergenciaBSMapper;

    public ContatoDeEmergenciaBSController(ContatoDeEmergenciaBSService contatoDeEmergenciaBSService, ContatoDeEmergenciaBSMapper contatoDeEmergenciaBSMapper) {
        this.contatoDeEmergenciaBSService = contatoDeEmergenciaBSService;
        this.contatoDeEmergenciaBSMapper = contatoDeEmergenciaBSMapper;
    }

    @Operation(summary = "Cria um novo contato de emergência")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Contato de emergência criado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ContatoDeEmergenciaBSResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados de cadastro inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Já existe um contato de emergência cadastrado com este telefone",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateContatoDeEmergenciaBSRequestDto contatoDeEmergenciaDto) {
        ContatoDeEmergenciaBS contatoDeEmergenciaBS = contatoDeEmergenciaBSMapper.fromCreateContatoDeEmergenciaBSRequestDtoToEntity(contatoDeEmergenciaDto);
        UUID uuid = contatoDeEmergenciaBSService.create(contatoDeEmergenciaBS);
        URI location = URI.create("/contatosDeEmergencia/" + uuid);
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Lista os contatos de emergência existentes")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contato de emergência encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDeEmergenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<ContatoDeEmergenciaBSResponseDto>> list() {
        List<ContatoDeEmergenciaBS> contatoDeEmergencia = contatoDeEmergenciaBSService.findAll();
        List<ContatoDeEmergenciaBSResponseDto> contatosDeEmergenciaResponseDtos = contatoDeEmergenciaBSMapper.fromContatoDeEmergenciaBSToDto(contatoDeEmergencia);
        return ResponseEntity.ok().body(contatosDeEmergenciaResponseDtos);
    }

    @Operation(summary = "Busca um contato de emergência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contato de emergência encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDeEmergenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contato de emergência não encontrado",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDeEmergenciaBSResponseDto> findById(@PathVariable UUID id) {
        ContatoDeEmergenciaBS contatoDeEmergenciaBS = contatoDeEmergenciaBSService.findById(id);
        return ResponseEntity.ok().body(contatoDeEmergenciaBSMapper.fromContatoDeEmergenciaBSToDto(contatoDeEmergenciaBS));
    }

    @Operation(summary = "Atualiza um contato de emergência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contato de emergência atualizada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDeEmergenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contato de emergência não encontrado",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDeEmergenciaBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateContatoDeEmergenciaBSRequestDto contatoDeEmergenciaDto) {
        ContatoDeEmergenciaBS contatoDeEmergenciaBS = contatoDeEmergenciaBSMapper.fromUpdateContatoDeEmergenciaBSRequestDtoToEntity(contatoDeEmergenciaDto);
        ContatoDeEmergenciaBS updated = contatoDeEmergenciaBSService.update(contatoDeEmergenciaBS, id);
        return ResponseEntity.ok().body(contatoDeEmergenciaBSMapper.fromContatoDeEmergenciaBSToDto(updated));
    }

    @Operation(summary = "Apaga um contato de emergência pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contato de emergência apagado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ContatoDeEmergenciaBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contato de emergência não encontrado",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contatoDeEmergenciaBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
