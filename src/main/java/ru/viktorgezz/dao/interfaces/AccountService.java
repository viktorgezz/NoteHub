package ru.viktorgezz.dao.interfaces;

import ru.viktorgezz.model.Account;

import java.sql.SQLException;
import java.util.Optional;

public interface AccountService {

    void save(Account account) throws SQLException;

    Optional<Account> findById(Long id) throws SQLException;

    Optional<Account> findByLogin(String login) throws SQLException;
}
