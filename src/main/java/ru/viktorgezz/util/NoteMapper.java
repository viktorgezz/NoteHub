package ru.viktorgezz.util;

import ru.viktorgezz.model.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class NoteMapper {

    public Note mapRowInNote(ResultSet rs) throws SQLException {
        return new Note.Builder()
                .setTitle(rs.getString("title"))
                .setText(rs.getString("txt"))
                .setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime())
                //.setCreatedAt(LocalDateTime.from(rs.getTimestamp("created_at").toLocalDateTime()))
                .build();
    }
}
