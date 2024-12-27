package ru.viktorgezz;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.viktorgezz.dao.AccountDao;
import ru.viktorgezz.model.Account;
import ru.viktorgezz.dao.interfaces.AccountService;
import ru.viktorgezz.servlet.RegisterServlet;
import ru.viktorgezz.util.mapper.JsonMapperImp;

import java.io.*;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class RegisterServletTest {

    @InjectMocks
    private RegisterServlet servlet;

    @Mock
    private AccountService accountService;
    @Mock
    private AccountDao accountDao;

    @Mock
    private JsonMapperImp jsonMapperImp;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Account account;

    @Mock
    private Account.Builder builder;

    @Mock
    BufferedReader mockReader;

    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoPost() throws SQLException, IOException, ServletException {
//        AccountDto accountDto = new AccountDto("user0", "123");
//        String jsonInput = "{\"login\":\"user0\", \"password\":\"123\"}";
//        when(mockRequest.getReader()).thenReturn(new BufferedReader(new StringReader(jsonInput)));
//
//        when(jsonHandler.get(any(mockReader.getClass()), eq(AccountDto.class))).thenReturn(accountDto);
//        when(accountRepo.findByLogin(accountDto.getLogin())).thenReturn(Optional.empty());
//
//        when(mockResponse.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
//
//
//        account = new Account.Builder()
//                .setId(0)
//                .setLogin(accountDto.getLogin())
//                .setPassword(accountDto.getPassword())
//                .build();
//        when(builder.build()).thenReturn(account);
//
//        doNothing().when(accountRepo).save(any(Account.class));
//
//        doNothing().when(jsonHandler).send(any(String.class), any(HttpServletResponse.class), any(int.class));
//
//        servlet.doPost(mockRequest, mockResponse);
//
////        verify(accountRepo).save(argThat(account ->
////                account.getLogin().equals("user0") && account.getPassword().equals("123")
////        ));
//        verify(jsonHandler).send("Аккаунт создан", mockResponse, 201);
    }
}
