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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody LogInAccountsBSRequestDto request
    ) {
        String token = authService.login(request);

        return ResponseEntity.ok(
                new AuthResponseDto(token)
        );
    }

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
