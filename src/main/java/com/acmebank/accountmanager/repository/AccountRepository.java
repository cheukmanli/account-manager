package com.acmebank.accountmanager.repository;

import com.acmebank.accountmanager.entity.Account;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findById(Integer id);
}
