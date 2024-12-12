package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dao.interfaces.NoteService;
import ru.viktorgezz.util.JsonMapper;
import ru.viktorgezz.util.JsonMapperImp;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/notes")
public class NotesServlet extends HttpServlet {

    private final JsonMapper jsonMapper = JsonMapperImp.getInstance();
    private final NoteService noteService = NoteDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jsonMapper.send(
                    noteService.findAllByIndexAccount(
                            Long.parseLong(
                                    req.getSession().getAttribute("userId").toString())),
                    resp,
                    200);
        } catch (SQLException e) {
            jsonMapper.send(e.getMessage(), resp, 500);
        }
    }
}
