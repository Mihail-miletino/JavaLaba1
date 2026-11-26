package model;

import java.time.LocalDateTime;

public class News {
    private int id;
    private String title;
    private String text;
    private String imageUrl;
    private LocalDateTime publishDate;
    private int likes;
    private int categoryId; // Связь с категорией

    public News() {}

    public News(int id, String title, String text, String imageUrl, LocalDateTime publishDate, int likes, int categoryId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.likes = likes;
        this.categoryId = categoryId;
    }

    // Геттеры и сеттеры для всех полей
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public LocalDateTime getPublishDate() { return publishDate; }
    public void setPublishDate(LocalDateTime publishDate) { this.publishDate = publishDate; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
