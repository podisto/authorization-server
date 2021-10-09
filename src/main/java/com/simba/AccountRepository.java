package com.simba;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by podisto on 09/10/2021.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByUsername(String username);
}
