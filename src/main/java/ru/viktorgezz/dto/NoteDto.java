package ru.viktorgezz.dto;

import java.sql.Timestamp;

public class NoteDto {
    private String title;

    private String txt;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Long idAccount;

    public NoteDto() {
    }

    public NoteDto(String title, String txt) {
        this.title = title;
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "title='" + title + '\'' +
                ", txt='" + txt + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", idAccount=" + idAccount +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }
}
