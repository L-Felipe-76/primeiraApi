package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.dto.request.LogInAccountsBSRequestDto;
import com.example.AccountsBS.dto.request.RegisterAccountBSRequestDto;

public interface AuthService {

    public String login(LogInAccountsBSRequestDto request);
    public String register(RegisterAccountBSRequestDto request);
}
