package ru.viktorgezz.util.mapper;

import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public interface JsonMapper {
    <T> T get(BufferedReader reader, Class<T> clazz) throws IOException;

    void send(Object object, HttpServletResponse resp, int status) throws IOException;

    void send(String message, HttpServletResponse resp, int status) throws IOException;
}
