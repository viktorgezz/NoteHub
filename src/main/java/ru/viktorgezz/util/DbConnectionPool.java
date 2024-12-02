package ru.viktorgezz.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dto.NoteDto;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionPool {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/note_db"); // change
        config.setUsername("postgres"); // change
        config.setPassword("v+L*=74"); // change
        config.setDriverClassName("org.postgresql.Driver");
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к БД");
        }
    }
}
