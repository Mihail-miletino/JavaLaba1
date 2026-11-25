package view.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO входящего запроса на создание новости.
 * Содержит только данные + правила валидации (Bean Validation).
 * Никакой логики отправки/сериализации здесь нет.
 */
public class CreateNewsRequest {

    @NotNull(message = "Поле title обязательно")
    @Size(min = 1, max = 200, message = "Длина title должна быть от 1 до 200 символов")
    private String title;

    @Size(max = 10000, message = "Длина text не должна превышать 10000 символов")
    private String text;

    public CreateNewsRequest() {}

    public CreateNewsRequest(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
