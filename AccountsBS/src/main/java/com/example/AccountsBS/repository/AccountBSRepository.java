package com.example.AccountsBS.repository;

//Spring Framework
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//AccountsBS
import com.example.AccountsBS.model.AccountBS;

//Dependencies
import java.util.UUID;
import java.util.Optional;

@Repository
public interface AccountBSRepository extends JpaRepository<AccountBS, UUID> {

    boolean existsByEmail(String email);
    Optional<AccountBS> findByEmail(String email);
}
