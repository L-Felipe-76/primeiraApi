package com.example.AccountsBS.service.impl;

import com.example.AccountsBS.model.AccountBS;
import com.example.AccountsBS.repository.AccountBSRepository;
import com.example.AccountsBS.service.AccountBSService;
import com.example.AccountsBS.exception.AccountNotFoundException;
import com.example.AccountsBS.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountBSServiceImpl implements AccountBSService{

    private final AccountBSRepository accountBSRepository;

    public AccountBSServiceImpl(AccountBSRepository accountBSRepository) {
        this.accountBSRepository = accountBSRepository;
    }

    @Override
    public UUID create(AccountBS account) {
        if (accountBSRepository.existsByEmail(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }
        return accountBSRepository.save(account).getId();
    }

    @Override
    public AccountBS update(AccountBS account, UUID uuid) {
        AccountBS existing = findById(uuid);
        if(!existing.getEmail().equals(account.getEmail()) && accountBSRepository.existsByEmail(account.getEmail())) {
            throw new EmailAlreadyExistsException(account.getEmail());
        }
        existing.setNomeCompleto(account.getNomeCompleto());
        existing.setGenero(account.getGenero());
        existing.setDataDeNascimento(account.getDataDeNascimento());
        existing.setEmail(account.getEmail());
        existing.setPassword(account.getPassword());
        existing.setTelefone(account.getTelefone());
        existing.setCep(account.getCep());
        existing.setEstado(account.getEstado());
        existing.setCidade(account.getCidade());
        existing.setBairro(account.getBairro());
        existing.setRua(account.getRua());
        existing.setNumeroCasa(account.getNumeroCasa());
        existing.setReferenciasRua(account.getReferenciasRua());
        return accountBSRepository.save(existing);
    }

    @Override
    public List<AccountBS> findAll(){
        return accountBSRepository.findAll();
    }

    @Override
    public AccountBS findById(UUID uuid) {
        return accountBSRepository.findById(uuid).orElseThrow(() -> new AccountNotFoundException(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        if(!accountBSRepository.existsById(uuid)){
            throw new AccountNotFoundException(uuid);
        }
        accountBSRepository.deleteById(uuid);
    }
}
