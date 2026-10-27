package com.example.AccountsBS.service.impl;

//Spring Framework
import org.springframework.stereotype.Service;

//AccountsBS
import com.example.AccountsBS.model.MoradorBS;
import com.example.AccountsBS.repository.MoradorBSRepository;
import com.example.AccountsBS.service.MoradorBSService;
import com.example.AccountsBS.exception.MoradorAlreadyExistsException;
import com.example.AccountsBS.exception.MoradorNotFoundException;

//Dependencies
import java.util.List;
import java.util.UUID;

@Service
public class MoradorBSServiceImpl implements MoradorBSService{

    private final MoradorBSRepository moradorBSRepository;

    public MoradorBSServiceImpl(MoradorBSRepository moradorBSRepository) {
        this.moradorBSRepository = moradorBSRepository;
    }

    @Override
    public UUID create(MoradorBS moradorBS) {
        if (moradorBSRepository.existsByNomeCompletoAndDataDeNascimento(moradorBS.getNomeCompleto(), moradorBS.getDataDeNascimento())) {
            throw new MoradorAlreadyExistsException(moradorBS.getNomeCompleto());
        }
        return moradorBSRepository.save(moradorBS).getId();
    }

    @Override
    public MoradorBS update(MoradorBS moradorBS, UUID uuid) {
        MoradorBS existing = findById(uuid);
        if(!existing.getNomeCompleto().equals(moradorBS.getNomeCompleto()) && moradorBSRepository.existsByNomeCompletoAndDataDeNascimento(moradorBS.getNomeCompleto(), moradorBS.getDataDeNascimento())) {
            throw new MoradorAlreadyExistsException(moradorBS.getNomeCompleto());
        }
        existing.setNomeCompleto(moradorBS.getNomeCompleto());
        existing.setGenero(moradorBS.getGenero());
        existing.setDataDeNascimento(moradorBS.getDataDeNascimento());
        existing.setTelefone(moradorBS.getTelefone());
        return moradorBSRepository.save(existing);
    }

    @Override
    public List<MoradorBS> findAll() {
        return moradorBSRepository.findAll();
    }

    @Override
    public MoradorBS findById(UUID uuid) {
        return moradorBSRepository.findById(uuid).orElseThrow(() -> new MoradorNotFoundException(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        if(!moradorBSRepository.existsById(uuid)){
            throw new MoradorNotFoundException(uuid);
        }
        moradorBSRepository.deleteById(uuid);
    }
}
