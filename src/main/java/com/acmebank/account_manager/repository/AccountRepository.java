package com.acmebank.account_manager.repository;

import com.acmebank.account_manager.entity.Account;
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
