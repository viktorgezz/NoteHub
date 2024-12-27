package ru.viktorgezz.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Note {

    private Long id;

    private String title;

    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    private Long idAccount;

    private Note() {
    }

    private Note(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.createdAt = builder.createdAt;
        this.updateAt = builder.updateAt;
        this.idAccount = builder.idAccount;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {
        private Long id;
        private String title;
        private String text;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;
        private Long idAccount;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

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

        public Builder setUpdateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public Builder setIdAccount(Long idAccount) {
            this.idAccount = idAccount;
            return this;
        }

        public Note build() {
            if (title == null || text == null || createdAt == null || updateAt == null || title.isEmpty() || text.isEmpty() || idAccount == null) {
                throw new RuntimeException("Ошибка создания заметки");
            }
            return new Note(this);
        }
    }
}
