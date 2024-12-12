package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import ru.viktorgezz.dao.AccountDao;
import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dao.interfaces.NoteService;
import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.util.CustomException;
import ru.viktorgezz.util.JsonMapper;
import ru.viktorgezz.util.JsonMapperImp;
import ru.viktorgezz.util.NoteValidation;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/note")
public class NoteServlet extends HttpServlet {

    private final JsonMapper jsonMapper = JsonMapperImp.getInstance();
    private final NoteService noteService = NoteDao.getInstance();
    private final NoteValidation noteValidation= new NoteValidation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        try {
            jsonMapper.send(
                    noteService.findById(
                            Long.parseLong(req.getParameter("id")))
                            .orElseThrow(() -> new CustomException("Заметка не найдена")), resp, 200);
        } catch (SQLException e) {
            jsonMapper.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonMapper.send(e.getMessage(), resp, 400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        NoteDto noteDto = jsonMapper.get(req.getReader(), NoteDto.class);
        noteDto.setIdAccount(Long.parseLong(req.getSession().getAttribute("userId").toString()));

        try {
            noteValidation.validate(noteDto);
            noteService.save(noteDto);
            jsonMapper.send("Заметка сохранена", resp, 200);
        } catch (SQLException e) {
            jsonMapper.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonMapper.send(e.getMessage(), resp, 400);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        try {
            long id = Long.parseLong(req.getParameter("id"));
            noteValidation.isExistsEntity(id);
            noteService.deleteById(id);
            jsonMapper.send("Заметка удалена", resp, 200);
        } catch (SQLException e) {
            jsonMapper.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonMapper.send(e.getMessage(), resp, 400);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        long id = Long.parseLong(req.getParameter("id"));
        NoteDto noteDto = jsonMapper.get(req.getReader(), NoteDto.class);

        try {
            noteValidation.validate(id, noteDto);
            noteService.update(noteDto, id);
            jsonMapper.send("Заметка обновлена", resp, 200);
        } catch (CustomException e) {
            jsonMapper.send(e.getMessage(), resp, 400);
        } catch (SQLException e) {
            jsonMapper.send(e.getMessage(), resp, 500);
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
