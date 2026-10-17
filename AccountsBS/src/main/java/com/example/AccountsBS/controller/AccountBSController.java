package com.example.AccountsBS.controller;

//Spring Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

//AccountsBS
import com.example.AccountsBS.dto.request.UpdateAccountBSRequestDto;
import com.example.AccountsBS.dto.response.AccountBSResponseDto;
import com.example.AccountsBS.model.AccountBS;
import com.example.AccountsBS.service.AccountBSService;
import com.example.AccountsBS.mapper.AccountBSMapper;

//Dependencies
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("bairroSeguro/accounts")
public class AccountBSController {

    private final AccountBSService accountBSService;
    private final AccountBSMapper accountBSMapper;

    public AccountBSController(AccountBSService accountBSService, AccountBSMapper accountBSMapper) {
        this.accountBSService = accountBSService;
        this.accountBSMapper = accountBSMapper;
    }

    @GetMapping
    public ResponseEntity<List<AccountBSResponseDto>> list() {
        List<AccountBS> accounts = accountBSService.findAll();
        List<AccountBSResponseDto> accountResponseDtos = accountBSMapper.fromAccountBSToDto(accounts);
        return ResponseEntity.ok().body(accountResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> findById(@PathVariable UUID id) {
        AccountBS account = accountBSService.findById(id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountBSResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateAccountBSRequestDto accountDto) {
        AccountBS account = accountBSMapper.fromUpdateAccountBSRequestDtoToEntity(accountDto);
        AccountBS updated = accountBSService.update(account, id);
        return ResponseEntity.ok().body(accountBSMapper.fromAccountBSToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountBSService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
