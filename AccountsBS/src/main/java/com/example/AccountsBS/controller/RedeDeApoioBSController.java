package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.CreateRedeDeApoioBSRequestDto;
import com.example.AccountsBS.dto.request.UpdateRedeDeApoioBSRequestDto;
import com.example.AccountsBS.dto.response.RedeDeApoioBSResponseDto;
import com.example.AccountsBS.model.RedeDeApoioBS;
import com.example.AccountsBS.service.RedeDeApoioBSService;
import com.example.AccountsBS.mapper.RedeDeApoioBSMapper;

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

@Tag(name = "RedesDeApoioBS", description = "Endpoints e operações relacionados ao gerenciamento da redes de apoio no sistema BS")
@RestController
@RequestMapping("bairroSeguro/redesDeApoio")
public class RedeDeApoioBSController {

    private final RedeDeApoioBSService redeDeApoioBSService;
    private final RedeDeApoioBSMapper redeDeApoioBSMapper;

    public RedeDeApoioBSController(RedeDeApoioBSService redeDeApoioBSService, RedeDeApoioBSMapper redeDeApoioBSMapper) {
        this.redeDeApoioBSService = redeDeApoioBSService;
        this.redeDeApoioBSMapper = redeDeApoioBSMapper;
    }

    @Operation(summary = "Cria uma nova rede de apoio")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Rede de apoio criada com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = RedeDeApoioBSResponseDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados de cadastro inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Já existe uma rede apoio cadastrada com esse cep e bairro",
            content = @Content
        )
    })
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateRedeDeApoioBSRequestDto redeDeApoioDto) {
        RedeDeApoioBS redeDeApoioBS = redeDeApoioBSMapper.fromCreateRedeDeApoioBSRequestDtoToEntity(redeDeApoioDto);
        UUID uuid = redeDeApoioBSService.create(redeDeApoioBS);
        URI location = URI.create("/redesDeApoio/" + uuid);
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Lista as redes de apoio existentes")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Rede de apoio encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RedeDeApoioBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        )
    })
    @GetMapping
    public ResponseEntity<List<RedeDeApoioBSResponseDto>> list() {
        List<RedeDeApoioBS> redesDeApoio = redeDeApoioBSService.findAll();
        List<RedeDeApoioBSResponseDto> redesDeApoioResponseDtos = redeDeApoioBSMapper.fromRedeDeApoioBSToDto(redesDeApoio);
        return ResponseEntity.ok().body(redesDeApoioResponseDtos);
    }

    @Operation(summary = "Busca uma rede de apoio pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Rede de apoio encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RedeDeApoioBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Rede de apoio não encontrada",
            content = @Content
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<RedeDeApoioBSResponseDto> findById(@PathVariable UUID id) {
        RedeDeApoioBS redeDeApoioBS = redeDeApoioBSService.findById(id);
        return ResponseEntity.ok().body(redeDeApoioBSMapper.fromRedeDeApoioBSToDto(redeDeApoioBS));
    }

    @Operation(summary = "Atualiza uma rede de apoio pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Rede de apoio atualizada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RedeDeApoioBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Rede de apoio não encontrada",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<RedeDeApoioBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateRedeDeApoioBSRequestDto redeDeApoioDto) {
        RedeDeApoioBS redeDeApoioBS = redeDeApoioBSMapper.fromUpdateRedeDeApoioBSRequestDtoToEntity(redeDeApoioDto);
        RedeDeApoioBS updated = redeDeApoioBSService.update(redeDeApoioBS, id);
        return ResponseEntity.ok().body(redeDeApoioBSMapper.fromRedeDeApoioBSToDto(updated));
    }

    @Operation(summary = "Apaga uma rede de apoio pelo id informado")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Rede de apoio apagada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RedeDeApoioBSResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Acesso bloqueado por falta de autenticação",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Rede de apoio não encontrada",
            content = @Content
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        redeDeApoioBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
