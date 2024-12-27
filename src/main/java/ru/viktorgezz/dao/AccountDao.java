package ru.viktorgezz.dao;

import ru.viktorgezz.model.Account;
import ru.viktorgezz.dao.interfaces.AccountService;
import ru.viktorgezz.util.DbConnectionPool;
import ru.viktorgezz.util.mapper.ResultSetMapper;

import java.sql.*;
import java.util.Optional;

public class AccountDao implements AccountService {

    private static final AccountDao instance = new AccountDao();

    private AccountDao() {
    }

    public static AccountDao getInstance() {
        return instance;
    }

    private final ResultSetMapper resultSetMapperMapper = new ResultSetMapper();

    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO Account(login, password) VALUES (?, ?)";

        try (Connection connection = DbConnectionPool.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, account.getLogin());
            stmt.setString(2, account.getPassword());

            stmt.executeUpdate();
        }
    }

    public Optional<Account> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Account WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(resultSetMapperMapper.mapRowInAccount(rs));
        }
    }

    public Optional<Account> findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM Account WHERE login = ?";

        try (Connection connection = DbConnectionPool.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(resultSetMapperMapper.mapRowInAccount(rs));
        }
    }
}
