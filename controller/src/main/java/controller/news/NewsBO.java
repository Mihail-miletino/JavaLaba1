package controller.news;

import model.News;
import model.NewsStorage;
import java.util.List;
import java.util.stream.Collectors;

public class NewsBO {
    private final NewsStorage storage = NewsStorage.getInstance();

    public List<News> getAllNews() {
        return storage.getAll();
    }

    public News addNews(String title, String text, int categoryId) {
        return storage.add(title, text, categoryId);
    }

    public News getNewsById(int id) {
        return storage.getById(id);
    }

    public boolean deleteNews(int id) {
        return storage.delete(id);
    }

    public News updateNews(int id, String title, String text) {
        return storage.update(id, title, text);
    }

    // Алгоритм поиска по ключевым словам (Слайд 16)
    public List<News> searchByKeywords(String keyword) {
        return getAllNews().stream()
                .filter(n -> n.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        n.getText().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
