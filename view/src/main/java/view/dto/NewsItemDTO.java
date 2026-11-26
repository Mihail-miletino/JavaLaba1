package view.dto;

import java.time.LocalDateTime;

public class NewsItemDTO {
    private int id;
    private String title;
    private String text;
    private LocalDateTime publishDate;
    private int likes;
    private int categoryId;

    public NewsItemDTO(int id, String title, String text, LocalDateTime publishDate, int likes, int categoryId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.publishDate = publishDate;
        this.likes = likes;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getText() { return text; }
    public LocalDateTime getPublishDate() { return publishDate; }
    public int getLikes() { return likes; }
    public int getCategoryId() { return categoryId; }
}
