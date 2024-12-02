package ru.viktorgezz.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String getJdbcUrl() {
        return PROPERTIES.getProperty("db.jdbcUrl");
    }

    public static String getDriverClassName() {
        return PROPERTIES.getProperty("db.driverClassName");
    }

    public static String getUsername() {
        return PROPERTIES.getProperty("db.username");
    }

    public static String getPassword() {
        return PROPERTIES.getProperty("db.password");
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}