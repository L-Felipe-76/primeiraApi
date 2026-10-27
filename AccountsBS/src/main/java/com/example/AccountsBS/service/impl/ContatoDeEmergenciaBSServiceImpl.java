package com.example.AccountsBS.service.impl;

//Spring Framework
import org.springframework.stereotype.Service;

//AccountsBS
import com.example.AccountsBS.model.ContatoDeEmergenciaBS;
import com.example.AccountsBS.repository.ContatoDeEmergenciaBSRepository;
import com.example.AccountsBS.service.ContatoDeEmergenciaBSService;
import com.example.AccountsBS.exception.ContatoDeEmergenciaAlreadyExistsException;
import com.example.AccountsBS.exception.ContatoDeEmergenciaNotFoundException;

//Dependencies
import java.util.List;
import java.util.UUID;

@Service
public class ContatoDeEmergenciaBSServiceImpl implements ContatoDeEmergenciaBSService{

    private final ContatoDeEmergenciaBSRepository contatoDeEmergenciaBSRepository;

    public ContatoDeEmergenciaBSServiceImpl(ContatoDeEmergenciaBSRepository contatoDeEmergenciaBSRepository) {
        this.contatoDeEmergenciaBSRepository = contatoDeEmergenciaBSRepository;
    }

    @Override
    public UUID create(ContatoDeEmergenciaBS contatoDeEmergenciaBS) {
        if (contatoDeEmergenciaBSRepository.existsByTelefone(contatoDeEmergenciaBS.getTelefone())) {
            throw new ContatoDeEmergenciaAlreadyExistsException(contatoDeEmergenciaBS.getTelefone());
        }
        return contatoDeEmergenciaBSRepository.save(contatoDeEmergenciaBS).getId();
    }

    @Override
    public ContatoDeEmergenciaBS update(ContatoDeEmergenciaBS contatoDeEmergenciaBS, UUID uuid) {
        ContatoDeEmergenciaBS existing = findById(uuid);
        if(!existing.getTelefone().equals(contatoDeEmergenciaBS.getTelefone()) && contatoDeEmergenciaBSRepository.existsByTelefone(contatoDeEmergenciaBS.getTelefone())) {
            throw new ContatoDeEmergenciaAlreadyExistsException(contatoDeEmergenciaBS.getTelefone());
        }
        existing.setNomeCompleto(contatoDeEmergenciaBS.getNomeCompleto());
        existing.setTelefone(contatoDeEmergenciaBS.getTelefone());
        existing.setDescricao(contatoDeEmergenciaBS.getDescricao());
        return contatoDeEmergenciaBSRepository.save(existing);
    }

    @Override
    public List<ContatoDeEmergenciaBS> findAll() {
        return contatoDeEmergenciaBSRepository.findAll();
    }

    @Override
    public ContatoDeEmergenciaBS findById(UUID uuid) {
        return contatoDeEmergenciaBSRepository.findById(uuid).orElseThrow(() -> new ContatoDeEmergenciaNotFoundException(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        if(!contatoDeEmergenciaBSRepository.existsById(uuid)){
            throw new ContatoDeEmergenciaNotFoundException(uuid);
        }
        contatoDeEmergenciaBSRepository.deleteById(uuid);
    }
}
