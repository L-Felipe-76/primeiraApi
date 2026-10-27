package com.example.AccountsBS.repository;

//Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//AccountsBS
import com.example.AccountsBS.model.OcorrenciaBS;

//Dependencies
import java.util.UUID;

@Repository
public interface OcorrenciaBSRepository extends JpaRepository<OcorrenciaBS, UUID>{
}
