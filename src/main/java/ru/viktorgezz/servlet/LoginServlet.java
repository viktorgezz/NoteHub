package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.viktorgezz.dao.AccountDao;
import ru.viktorgezz.dto.AccountDto;
import ru.viktorgezz.model.Account;
import ru.viktorgezz.dao.interfaces.AccountService;
import ru.viktorgezz.security.PasswordHasher;
import ru.viktorgezz.util.mapper.JsonMapper;
import ru.viktorgezz.util.mapper.JsonMapperImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    private final JsonMapper jsonMapper = JsonMapperImp.getInstance();
    private final AccountService accountService = AccountDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDto accountDto = jsonMapper.get(req.getReader(), AccountDto.class);
        try {
            Optional<Account> foundAccount = accountService.findByLogin(accountDto.getLogin());
            if (foundAccount.isEmpty() ||
                    !foundAccount.get()
                            .getPassword()
                            .equals(
                                    PasswordHasher.hashPassword(accountDto.getPassword())
                            )) {
                jsonMapper.send("Неправильный логин или пароль", resp, 401);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("userId", foundAccount.get().getId());
            jsonMapper.send("", resp, 200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
