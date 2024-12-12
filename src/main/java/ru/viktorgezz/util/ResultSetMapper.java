package ru.viktorgezz.util;

import ru.viktorgezz.model.Note;
import ru.viktorgezz.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper {

    public Note mapRowInNote(ResultSet rs) throws SQLException {
        return new Note.Builder()
                .setTitle(rs.getString("title"))
                .setText(rs.getString("txt"))
                .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                .setUpdateAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .setIdAccount(rs.getLong("id_account"))
                .build();
    }

    public Account mapRowInAccount(ResultSet rs) throws SQLException {
        return new Account.Builder()
                .setId(rs.getInt("id"))
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .build();
    }
}
