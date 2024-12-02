package ru.viktorgezz.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.viktorgezz.util.CustomException;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Note {

    private String title;

    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private Note() {
    }

    private Note(Builder builder) {
        this.title = builder.title;
        this.text = builder.text;
        this.createdAt = builder.createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder {
        private String title;
        private String text;
        private LocalDateTime createdAt;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;

        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Note build() {
            if (title == null || text == null || createdAt == null) {
                throw new RuntimeException("Ошибка создания заметки");
            }
            return new Note(this);
        }
    }
}
