package ru.viktorgezz.dao;

import ru.viktorgezz.dao.interfaces.NoteService;
import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.model.Note;
import ru.viktorgezz.util.DbConnectionPool;
import ru.viktorgezz.util.mapper.ResultSetMapper;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class NoteDao implements NoteService {

    private static final NoteDao instance = new NoteDao();

    private NoteDao() {
    }

    public static NoteDao getInstance() {
        return instance;
    }

    private final ResultSetMapper resultSetMapper = new ResultSetMapper();

    public List<Note> findAllByIndexAccount(long idAccount) throws SQLException {
        String sql = "SELECT * FROM Note where id_account = ?";
        List<Note> notes = new LinkedList<>();

        try (Connection connection = DbConnectionPool.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idAccount);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                notes.add(resultSetMapper.mapRowInNote(rs));
            }
            return notes;
        }
    }

    public void save(NoteDto noteDto) throws SQLException {
        String sql = "INSERT INTO Note(title, txt, created_at, updated_at, id_account) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, noteDto.getTitle());
            stmt.setString(2, noteDto.getText());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(5, noteDto.getIdAccount());

            stmt.executeUpdate();
        }
    }

    public Optional<Note> findById(long id) throws SQLException {
        String sql = "SELECT * FROM Note WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return Optional.empty();
            }

            return Optional.of(resultSetMapper.mapRowInNote(rs));
        }
    }

    public void deleteById(long id) throws SQLException {
        String sql = "DELETE FROM Note WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void update(NoteDto noteDto) throws SQLException {
        String sql = "UPDATE Note SET title = ?, txt = ?, updated_at = ? WHERE id = ?";

        try (Connection connection = DbConnectionPool.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, noteDto.getTitle());
            stmt.setString(2, noteDto.getText());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setLong(4, noteDto.getId());
            stmt.executeUpdate();
        }
    }
}
