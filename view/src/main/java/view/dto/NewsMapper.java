package view.dto;

import model.News;

/**
 * Преобразование доменной сущности в DTO (и наоборот, если понадобится).
 * Это НЕ DTO — отдельный класс-маппер.
 */
public final class NewsMapper {

    private NewsMapper() {}

    public static NewsResponse toResponse(News news) {
        return new NewsResponse(news.getId(), news.getTitle(), news.getText());
    }
}
