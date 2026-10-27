package com.example.AccountsBS.service.impl;

//Spring Framework
import org.springframework.stereotype.Service;

//AccountsBS
import com.example.AccountsBS.model.OcorrenciaBS;
import com.example.AccountsBS.repository.OcorrenciaBSRepository;
import com.example.AccountsBS.service.OcorrenciaBSService;
import com.example.AccountsBS.exception.OcorrenciaNotFoundException;

//Dependencies
import java.util.List;
import java.util.UUID;

@Service
public class OcorrenciaBSServiceImpl implements OcorrenciaBSService{

    private final OcorrenciaBSRepository ocorrenciaBSRepository;

    public OcorrenciaBSServiceImpl(OcorrenciaBSRepository ocorrenciaBSRepository) {
        this.ocorrenciaBSRepository = ocorrenciaBSRepository;
    }

    @Override
    public UUID create(OcorrenciaBS ocorrenciaBS) {
        return ocorrenciaBSRepository.save(ocorrenciaBS).getId();
    }

    @Override
    public OcorrenciaBS update(OcorrenciaBS ocorrenciaBS, UUID uuid) {
        OcorrenciaBS existing = findById(uuid);

        existing.setTituloOcorrencia(ocorrenciaBS.getTituloOcorrencia());
        existing.setDescricao(ocorrenciaBS.getDescricao());
        existing.setTag(ocorrenciaBS.getTag());
        return ocorrenciaBSRepository.save(existing);
    }

    @Override
    public List<OcorrenciaBS> findAll() {
        return ocorrenciaBSRepository.findAll();
    }

    @Override
    public OcorrenciaBS findById(UUID uuid) {
        return ocorrenciaBSRepository.findById(uuid).orElseThrow(() -> new OcorrenciaNotFoundException(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        if(!ocorrenciaBSRepository.existsById(uuid)){
            throw new OcorrenciaNotFoundException(uuid);
        }
        ocorrenciaBSRepository.deleteById(uuid);
    }
}
