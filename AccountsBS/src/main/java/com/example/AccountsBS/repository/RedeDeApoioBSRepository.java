package com.example.AccountsBS.repository;

//Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//AccountsBS
import com.example.AccountsBS.model.RedeDeApoioBS;

//Dependencies
import java.util.UUID;

@Repository
public interface RedeDeApoioBSRepository extends JpaRepository<RedeDeApoioBS, UUID>{

    boolean existsByCepAndBairro(String cep, String bairro);
}
