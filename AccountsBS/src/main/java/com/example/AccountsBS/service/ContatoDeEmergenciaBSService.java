package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.model.ContatoDeEmergenciaBS;

//Dependencies
import java.util.List;
import java.util.UUID;

public interface ContatoDeEmergenciaBSService {

    UUID create(ContatoDeEmergenciaBS contatoDeEmergenciaBS);
    ContatoDeEmergenciaBS update(ContatoDeEmergenciaBS contatoDeEmergenciaBS, UUID uuid);
    List<ContatoDeEmergenciaBS> findAll();
    ContatoDeEmergenciaBS findById(UUID uuid);
    void delete(UUID uuid);
}
