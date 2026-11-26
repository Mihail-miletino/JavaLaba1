package view.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateNewsRequest {
    @NotNull(message = "Поле title обязательно")
    @Size(min = 1, max = 200)
    private String title;

    private String text;

    @NotNull(message = "Поле categoryId обязательно")
    private Integer categoryId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
}
