package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.util.CustomException;
import ru.viktorgezz.util.JsonHandler;
import ru.viktorgezz.util.NoteValidation;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/note")
public class NoteServlet extends HttpServlet {

    private final JsonHandler jsonHandler = new JsonHandler();
    private final NoteDao noteDao = new NoteDao();
    private final NoteValidation noteValidation= new NoteValidation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        try {
            long id = Long.parseLong(req.getParameter("id"));
            jsonHandler.send(noteDao.findById(id).orElseThrow(() -> new CustomException("Заметка не найдена")), resp, 200);
        } catch (SQLException e) {
            jsonHandler.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonHandler.send(e.getMessage(), resp, 400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        NoteDto noteDto = jsonHandler.get(req.getReader(), NoteDto.class);
        try {
            noteValidation.validate(noteDto);
            noteDao.save(noteDto);
            jsonHandler.send("Заметка сохранена", resp, 200);
        } catch (SQLException e) {
            jsonHandler.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonHandler.send(e.getMessage(), resp, 400);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        long id = Long.parseLong(req.getParameter("id"));
        try {
            noteValidation.isExistsEntity(id);
            noteDao.deleteById(id);
            jsonHandler.send("Заметка удалена", resp, 200);
        } catch (SQLException e) {
            jsonHandler.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonHandler.send(e.getMessage(), resp, 400);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        long id = Long.parseLong(req.getParameter("id"));
        NoteDto noteDto = jsonHandler.get(req.getReader(), NoteDto.class);
        try {
            noteValidation.validate(id, noteDto);
            noteDao.update(noteDto, id);
        } catch (SQLException e) {
            jsonHandler.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonHandler.send(e.getMessage(), resp, 400);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod().toUpperCase();
        switch (method) {
            case "GET":
                doGet(req, resp);
                break;
            case "POST":
                doPost(req, resp);
                break;
            case "DELETE":
                doDelete(req, resp);
                break;
            case "PATCH":
                doPatch(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Метод " + method + " не поддерживается");
        }
    }

}
