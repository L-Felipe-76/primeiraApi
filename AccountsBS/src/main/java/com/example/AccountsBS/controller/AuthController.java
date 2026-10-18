package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//AccountsBS
import com.example.AccountsBS.dto.response.AuthResponseDto;
import com.example.AccountsBS.dto.request.LogInAccountsBSRequestDto;
import com.example.AccountsBS.dto.request.RegisterAccountBSRequestDto;
import com.example.AccountsBS.service.impl.AuthServiceImpl;

//Dependencies
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(name = "AuthBS", description = "Endpoints e operações relacionados ao registro e login de contas no sistema BS")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @Operation(
            summary = "Realizar login em uma conta"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ocorre quando dados de login incorretos ou inexistentes são usados tanto para email para senha, seja de forma individual ou em conjunto",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody LogInAccountsBSRequestDto request
    ) {
        String token = authService.login(request);

        return ResponseEntity.ok(
                new AuthResponseDto(token)
        );
    }

    @Operation(
            summary = "Registrar uma nova conta",
            description = " Cria uma nova conta no sistema utilizando os dados informados. Após o cadastro, um token JWT é retornado para autenticação nas rotas protegidas da API. "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Conta criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDto.class)
                    )
            ),
            @ApiResponse(
            responseCode = "400",
            description = "Dados de cadastro inválidos",
            content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Já existe uma conta cadastrada com o e-mail informado",
                    content = @Content
            )
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody RegisterAccountBSRequestDto request
    ) {
        String token = authService.register(request);

        return ResponseEntity.ok(
                new AuthResponseDto(token)
        );
    }
}
