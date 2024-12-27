package ru.viktorgezz.util;

import ru.viktorgezz.dto.AccountDto;
import ru.viktorgezz.model.Account;

public class AccountConverter {

    public Account convertAccountDtoToAccount(AccountDto accountDto) {
        return new Account.Builder()
                .setId(0) //
                .setLogin(accountDto.getLogin())
                .setPassword(accountDto.getPassword())
                .build();
    }
}
