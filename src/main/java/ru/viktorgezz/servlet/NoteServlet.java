package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.util.AcceptParam;
import ru.viktorgezz.util.CustomException;
import ru.viktorgezz.util.JsonHandler;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/note")
public class NoteServlet extends HttpServlet {

    private final JsonHandler jsonHandler = new JsonHandler();
    private final NoteDao noteDao = new NoteDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        NoteDto noteDto = jsonHandler.get(req.getReader(), NoteDto.class);
        // validation
        try {
            noteDao.save(noteDto);
            jsonHandler.send("Заметка сохранена", resp, 200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.APPLICATION_JSON);
        try {
            long id = Long.parseLong(req.getParameter("id"));
            jsonHandler.send(noteDao.findById(id).orElseThrow(() -> new CustomException("Заметка не найдена")), resp, 200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

//    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getMethod().equalsIgnoreCase("PATCH"))
//            doPatch(req, resp);
//        else
//            super.service(req, resp);
//    }
}
