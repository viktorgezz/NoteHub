package ru.viktorgezz.dao.interfaces;

import ru.viktorgezz.dto.NoteDto;
import ru.viktorgezz.model.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    List<Note> findAllByIndexAccount(long idAccount) throws SQLException;

    void save(NoteDto noteDto) throws SQLException;

    Optional<Note> findById(long id) throws SQLException;

    void deleteById(long id) throws SQLException;

    void update(NoteDto noteDto, long id) throws SQLException;
}
