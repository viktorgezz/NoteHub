package ru.viktorgezz.util;

import ru.viktorgezz.dao.NoteDao;
import ru.viktorgezz.dao.interfaces.NoteService;
import ru.viktorgezz.dto.NoteDto;

import java.sql.SQLException;

public class NoteValidation {

    private final NoteService noteService = NoteDao.getInstance();


    public void validateUpdate(NoteDto noteDto) throws SQLException, CustomException {
        isExistsEntity(noteDto.getId());

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
        if (noteService.findById(id).isEmpty()) {
            throw new CustomException("Запись не найдена");
        }
    }
    
    private StringBuilder checkEmpty(NoteDto noteDto) {
        StringBuilder errors = new StringBuilder();
        
        if (noteDto.getTitle().isEmpty()) {
            errors.append("Заголовок не может быть пустым");
        }

        if (noteDto.getText().isEmpty()) {
            errors.append("Текст не может быть пустым");
        }
        
        return errors;
    }
}
