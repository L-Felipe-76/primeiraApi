package com.example.AccountsBS.service.impl;

//Spring Framework
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//AccountsBS
import com.example.AccountsBS.dto.request.LogInAccountsBSRequestDto;
import com.example.AccountsBS.dto.request.RegisterAccountBSRequestDto;
import com.example.AccountsBS.model.AccountBS;
import com.example.AccountsBS.repository.AccountBSRepository;
import com.example.AccountsBS.service.AuthService;

//Dependencies
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final AccountBSRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTServiceImpl jwtService;

    public String login(LogInAccountsBSRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        AccountBS account = accountRepository
                .findByEmail(request.email())
                .orElseThrow();

        return jwtService.generateToken(account);
    }

    public String register(RegisterAccountBSRequestDto request) {

        AccountBS account = new AccountBS();

        account.setEmail(request.email());
        account.setPassword(
                passwordEncoder.encode(request.password())
        );

        accountRepository.save(account);

        return jwtService.generateToken(account);
    }
}
