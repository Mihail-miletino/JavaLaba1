package view.dto;

import model.Category;
import model.News;

public class Mapper {
    public static NewsItemDTO toDTO(News n) {
        return new NewsItemDTO(n.getId(), n.getTitle(), n.getText(), n.getPublishDate(), n.getLikes(), n.getCategoryId());
    }

    public static CategoryDTO toDTO(Category c) {
        return new CategoryDTO(c.getId(), c.getName());
    }
}
