package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.viktorgezz.util.mapper.JsonMapper;
import ru.viktorgezz.util.mapper.JsonMapperImp;

import java.io.IOException;

@WebServlet("/api/logout")
public class LogoutServlet extends HttpServlet {

    private final JsonMapper jsonMapper = JsonMapperImp.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // получаем существующую сессию
        if (session != null) {
            session.invalidate(); // очищаем сессию
        }
        jsonMapper.send("Успешный выход", resp, 200);

    }
}
