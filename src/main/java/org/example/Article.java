package org.example;

import java.time.LocalDateTime;

public class Article {
    private final int id;
    private final int memberId;
    private final LocalDateTime createdAt;
    private String title;
    private String content;
    private LocalDateTime updatedAt;

    public Article(int id, String title, String content,
                   LocalDateTime createdAt, LocalDateTime updatedAt, int memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
