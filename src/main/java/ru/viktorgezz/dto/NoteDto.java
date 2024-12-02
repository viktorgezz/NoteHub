package ru.viktorgezz.dto;

import java.sql.Timestamp;

public class NoteDto {
    private String title;

    private String txt;

    private Timestamp createdAt;

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
}
