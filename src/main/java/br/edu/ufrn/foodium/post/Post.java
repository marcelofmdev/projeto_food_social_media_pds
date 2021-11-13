package br.edu.ufrn.foodium.post;

import java.time.LocalDate;

public class Post {
    private Long id;
    private Long userId;
    private String imageUrl;
    private String content;
    private LocalDate date;

    public Post() {
    }

    public Post(Long id,
                Long userId,
                String imageUrl,
                String content,
                LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.content = content;
        this.date = date;
    }

    public Post(Long userId,
                String imageUrl,
                String content,
                LocalDate date) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
