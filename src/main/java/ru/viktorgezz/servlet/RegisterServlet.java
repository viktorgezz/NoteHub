package ru.viktorgezz.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.viktorgezz.dao.AccountDao;
import ru.viktorgezz.dto.AccountDto;
import ru.viktorgezz.model.Account;
import ru.viktorgezz.dao.interfaces.AccountService;
import ru.viktorgezz.security.PasswordHasher;
import ru.viktorgezz.util.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final JsonMapper jsonMapperImp = JsonMapperImp.getInstance();
    private final AccountService accountService = AccountDao.getInstance();
    private final AccountConverter accountConverter = new AccountConverter();
    private final AccountValidation accountValidation = new AccountValidation();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDto accountDto = jsonMapperImp.get(req.getReader(), AccountDto.class);
        try {
            accountValidation.validate(accountDto);
            if (accountService.findByLogin(accountDto.getLogin()).isPresent()) {
                jsonMapperImp.send("Такой логин уже существует", resp, 409);
                return;
            }

            Account account = accountConverter.convertAccountDtoToAccount(accountDto);
            account.setPassword(PasswordHasher.hashPassword(accountDto.getPassword()));
            accountService.save(account);
            jsonMapperImp.send("Аккаунт создан", resp, 201);
        } catch (SQLException e) {
            jsonMapperImp.send(e.getMessage(), resp, 500);
        } catch (CustomException e) {
            jsonMapperImp.send(e.getMessage(), resp, 400);
        }
    }
}
