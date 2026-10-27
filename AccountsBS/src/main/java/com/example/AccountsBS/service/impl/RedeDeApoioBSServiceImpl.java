package com.example.AccountsBS.service.impl;

//Spring Framework
import org.springframework.stereotype.Service;

//AccountsBS
import com.example.AccountsBS.model.RedeDeApoioBS;
import com.example.AccountsBS.repository.RedeDeApoioBSRepository;
import com.example.AccountsBS.service.RedeDeApoioBSService;
import com.example.AccountsBS.exception.RedeDeApoioAlreadyExistsException;
import com.example.AccountsBS.exception.RedeDeApoioNotFoundException;

//Dependencies
import java.util.List;
import java.util.UUID;

@Service
public class RedeDeApoioBSServiceImpl implements RedeDeApoioBSService{

    private final RedeDeApoioBSRepository redeDeApoioBSRepository;

    public RedeDeApoioBSServiceImpl(RedeDeApoioBSRepository redeDeApoioBSRepository) {
        this.redeDeApoioBSRepository = redeDeApoioBSRepository;
    }

    @Override
    public UUID create(RedeDeApoioBS redeDeApoioBS) {
        if (redeDeApoioBSRepository.existsByCepAndBairro(redeDeApoioBS.getCep(), redeDeApoioBS.getBairro())) {
            throw new RedeDeApoioAlreadyExistsException(redeDeApoioBS.getCep(), redeDeApoioBS.getBairro());
        }
        return redeDeApoioBSRepository.save(redeDeApoioBS).getId();
    }

    @Override
    public RedeDeApoioBS update(RedeDeApoioBS redeDeApoioBS, UUID uuid) {
        RedeDeApoioBS existing = findById(uuid);
        if(!existing.getCep().equals(redeDeApoioBS.getCep()) && !existing.getBairro().equals(redeDeApoioBS.getBairro()) && redeDeApoioBSRepository.existsByCepAndBairro(redeDeApoioBS.getCep(), redeDeApoioBS.getBairro())) {
            throw new RedeDeApoioAlreadyExistsException(redeDeApoioBS.getCep(), redeDeApoioBS.getBairro());
        }
        existing.setCep(redeDeApoioBS.getCep());
        existing.setEstado(redeDeApoioBS.getEstado());
        existing.setCidade(redeDeApoioBS.getCidade());
        existing.setBairro(redeDeApoioBS.getBairro());
        existing.setIntegrantes(redeDeApoioBS.getIntegrantes());
        return redeDeApoioBSRepository.save(existing);
    }

    @Override
    public List<RedeDeApoioBS> findAll() {
        return redeDeApoioBSRepository.findAll();
    }

    @Override
    public RedeDeApoioBS findById(UUID uuid) {
        return redeDeApoioBSRepository.findById(uuid).orElseThrow(() -> new RedeDeApoioNotFoundException(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        if(!redeDeApoioBSRepository.existsById(uuid)){
            throw new RedeDeApoioNotFoundException(uuid);
        }
        redeDeApoioBSRepository.deleteById(uuid);
    }
}
