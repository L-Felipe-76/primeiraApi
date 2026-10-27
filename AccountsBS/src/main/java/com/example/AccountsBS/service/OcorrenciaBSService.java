package com.example.AccountsBS.service;

//AccountsBS
import com.example.AccountsBS.model.OcorrenciaBS;

//Dependencies
import java.util.List;
import java.util.UUID;

public interface OcorrenciaBSService {

    UUID create(OcorrenciaBS ocorrenciaBS);
    OcorrenciaBS update(OcorrenciaBS ocorrenciaBS, UUID uuid);
    List<OcorrenciaBS> findAll();
    OcorrenciaBS findById(UUID uuid);
    void delete(UUID uuid);
}
