package ru.viktorgezz.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcceptParam {

    public static String getFirstParam(HttpServletRequest req, String titleParam) throws CustomException {
        try {
            InputStreamReader reader = new InputStreamReader(req.getInputStream());
            String[] par = new BufferedReader(reader).readLine().split("=");
            if (!par[0].equals(titleParam) || par.length != 2) {
                throw new CustomException("Неверный параметр");
            }
            return par[1];
        } catch (IOException e) {
            throw new CustomException("Ошибка обработки запроса");
        }
    }
}
