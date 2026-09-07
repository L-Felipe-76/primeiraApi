package com.example.AccountsBS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.AccountsBS.model.AccountBS;

import java.util.UUID;

@Repository
public interface AccountBSRepository extends JpaRepository<AccountBS, UUID> {
    boolean existsByEmail(String email);
}
