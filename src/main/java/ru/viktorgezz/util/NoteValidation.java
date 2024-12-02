package ru.viktorgezz.util;

import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dto.NoteDto;

import java.sql.SQLException;

public class NoteValidation {

    private final NoteDao noteDao = new NoteDao();


    public void validate(long id, NoteDto noteDto) throws SQLException, CustomException {
        isExistsEntity(id);

        String errors = checkEmpty(noteDto).toString();
        if (!errors.isEmpty()) {
            throw new CustomException(errors);
        }
    }

    public void validate(NoteDto noteDto) throws SQLException, CustomException {
        String errors = checkEmpty(noteDto).toString();
        if (!errors.isEmpty()) {
            throw new CustomException(errors);
        }

        if (!errors.toString().isEmpty()) {
            throw new CustomException(errors.toString());
        }
    }
    
    public void isExistsEntity(long id) throws SQLException, CustomException {
        if (noteDao.findById(id).isEmpty()) {
            throw new CustomException("Запись не найдена");
        }
    }
    
    private StringBuilder checkEmpty(NoteDto noteDto) {
        StringBuilder errors = new StringBuilder();
        
        if (noteDto.getTitle().isEmpty()) {
            errors.append("Заголовок не может быть пустым");
        }

        if (noteDto.getTxt().isEmpty()) {
            errors.append("Текст не может быть пустым");
        }
        
        return errors;
    }
}
