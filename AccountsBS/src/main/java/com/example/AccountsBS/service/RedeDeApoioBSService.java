package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.model.RedeDeApoioBS;

//Dependencies
import java.util.List;
import java.util.UUID;

public interface RedeDeApoioBSService {

    UUID create(RedeDeApoioBS redeDeApoioBS);
    RedeDeApoioBS update(RedeDeApoioBS redeDeApoioBS, UUID uuid);
    List<RedeDeApoioBS> findAll();
    RedeDeApoioBS findById(UUID uuid);
    void delete(UUID uuid);
}
