package ru.viktorgezz.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectionPool {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(PropertiesUtil.getJdbcUrl());
        config.setUsername(PropertiesUtil.getUsername());
        config.setPassword(PropertiesUtil.getPassword());
        config.setDriverClassName(PropertiesUtil.getDriverClassName());
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
