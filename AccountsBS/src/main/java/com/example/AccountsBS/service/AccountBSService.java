package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.model.AccountBS;

//Dependencies
import java.util.List;
import java.util.UUID;

public interface AccountBSService {

    UUID create(AccountBS account);
    AccountBS update(AccountBS account, UUID uuid);
    List<AccountBS> findAll();
    AccountBS findById(UUID uuid);
    void delete(UUID uuid);
}
