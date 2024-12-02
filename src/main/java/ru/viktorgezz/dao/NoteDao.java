package ru.viktorgezz.dao;

import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.model.Note;
import ru.viktorgezz.util.DbConnectionPool;
import ru.viktorgezz.util.NoteMapper;

import java.sql.*;
import java.util.Optional;

public class NoteDao {

    private final NoteMapper noteMapper = new NoteMapper();

    public void save(NoteDto noteDto) throws SQLException {
        String sql = "INSERT INTO Notes(title, txt, created_at) VALUES (?, ?, ?)";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, noteDto.getTitle());
            stmt.setString(2, noteDto.getTxt());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();
        }
    }

    public Optional<Note> findById(long id) throws SQLException {
        String sql = "SELECT * FROM Notes WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(noteMapper.mapRowInNote(rs));
        }
    }

    public void deleteById(long id) throws SQLException {
        String sql = "DELETE FROM Notes WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(NoteDto noteDto, long id) throws SQLException {
        String sql = "UPDATE Notes SET title = ?, txt = ? WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, noteDto.getTitle());
            stmt.setString(2, noteDto.getTxt());
            stmt.setLong(3, id);
            stmt.executeUpdate();
        }
    }
}
