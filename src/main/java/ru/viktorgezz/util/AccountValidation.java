package ru.viktorgezz.util;

import ru.viktorgezz.dto.AccountDto;

public class AccountValidation {

    public void validate(AccountDto accountDto) throws CustomException {
        if (accountDto.getPassword() == null || accountDto.getPassword().isEmpty()) {
            throw new CustomException("Пароль не может быть пустым");
        }
    }
}
