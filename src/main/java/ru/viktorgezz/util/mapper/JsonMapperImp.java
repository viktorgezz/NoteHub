package ru.viktorgezz.util.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapperImp implements JsonMapper {

    private static final JsonMapperImp instance = new JsonMapperImp();

    private JsonMapperImp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static JsonMapperImp getInstance() {
        return instance;
    }

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public <T> T get(BufferedReader reader, Class<T> clazz) throws IOException {
        if (reader == null || !reader.ready()) {
            throw new IOException("Invalid input data");
        }
        return objectMapper.readValue(reader, clazz); // добавить чтенние данного исключения
    }

    public void send(Object object, HttpServletResponse resp, int status) throws IOException {
        String json = objectMapper.writeValueAsString(object);
        resp.setStatus(status);
        resp.getWriter().write(json);
    }

    public void send(String message, HttpServletResponse resp, int status) throws IOException {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", message);
        String json = objectMapper.writeValueAsString(responseBody);
        resp.setStatus(status);
        resp.getWriter().write(json);
    }
}
