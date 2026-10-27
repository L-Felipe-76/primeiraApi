package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.model.MoradorBS;

//Dependencies
import java.util.List;
import java.util.UUID;

public interface MoradorBSService {

    UUID create(MoradorBS moradorBS);
    MoradorBS update(MoradorBS moradorBS, UUID uuid);
    List<MoradorBS> findAll();
    MoradorBS findById(UUID uuid);
    void delete(UUID uuid);
}
