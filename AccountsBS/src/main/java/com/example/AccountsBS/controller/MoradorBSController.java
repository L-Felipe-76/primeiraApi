package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateMoradorBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateMoradorBSRequestDto;
import com.example.AccountsBS.dto.response.MoradorBSResponseDto;
import com.example.AccountsBS.model.MoradorBS;
import com.example.AccountsBS.service.MoradorBSService;
import com.example.AccountsBS.mapper.MoradorBSMapper;

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

@Tag(name = "MoradoresBS", description = "Endpoints e operações relacionados ao gerenciamento de moradores no sistema BS")
@RestController
@RequestMapping("bairroSeguro/moradores")
public class MoradorBSController {

    private final MoradorBSService moradorBSService;
    private final MoradorBSMapper moradorBSMapper;

    public MoradorBSController(MoradorBSService moradorBSService, MoradorBSMapper moradorBSMapper) {
        this.moradorBSService = moradorBSService;
        this.moradorBSMapper = moradorBSMapper;
    }

    @Operation(summary = "Cria um novo morador")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Morador criado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MoradorBSResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados de cadastro inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Já existe um morador cadastrado com este nome e data de nascimento",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateMoradorBSRequestDto moradorDto) {
        MoradorBS moradorBS = moradorBSMapper.fromCreateMoradorBSRequestDtoToEntity(moradorDto);
        UUID uuid = moradorBSService.create(moradorBS);
        URI location = URI.create("/moradores/" + uuid);
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Lista os moradores existentes")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Morador encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoradorBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<MoradorBSResponseDto>> list() {
        List<MoradorBS> moradores = moradorBSService.findAll();
        List<MoradorBSResponseDto> moradoresResponseDtos = moradorBSMapper.fromMoradorBSToDto(moradores);
        return ResponseEntity.ok().body(moradoresResponseDtos);
    }

    @Operation(summary = "Busca um morador pelo id informado")
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
            description = "Morador não encontrado",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<MoradorBSResponseDto> findById(@PathVariable UUID id) {
        MoradorBS moradorBS = moradorBSService.findById(id);
        return ResponseEntity.ok().body(moradorBSMapper.fromMoradorBSToDto(moradorBS));
    }

    @Operation(summary = "Atualiza um morador pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Conta atualizada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoradorBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Morador não encontrado",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<MoradorBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateMoradorBSRequestDto moradorDto) {
        MoradorBS moradorBS = moradorBSMapper.fromUpdateMoradorBSRequestDtoToEntity(moradorDto);
        MoradorBS updated = moradorBSService.update(moradorBS, id);
        return ResponseEntity.ok().body(moradorBSMapper.fromMoradorBSToDto(updated));
    }

    @Operation(summary = "Apaga um morador pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Morador apagado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MoradorBSResponseDto.class))
    ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Morador não encontrado",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        moradorBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
