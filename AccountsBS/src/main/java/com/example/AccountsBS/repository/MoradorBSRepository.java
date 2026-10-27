package com.example.AccountsBS.repository;

//Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//AccountsBS
import com.example.AccountsBS.model.MoradorBS;

//Dependencies
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface MoradorBSRepository extends JpaRepository<MoradorBS, UUID>{

    boolean existsByNomeCompletoAndDataDeNascimento(String nomeCompleto, LocalDate dataDeNascimento);
}
