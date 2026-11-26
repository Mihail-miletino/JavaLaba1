package controller.category;

import controller.news.NewsBO;
import model.Category;
import model.CategoryStorage;
import model.News;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryBO {
    private final CategoryStorage storage = CategoryStorage.getInstance();
    private final NewsBO newsBO = new NewsBO(); // Для алгоритма со слайда 16

    public List<Category> getAllCategories() {
        return storage.getAll();
    }

    public Category addCategory(String name) {
        return storage.add(name);
    }

    // Получение категорий, по которым есть новости
    public List<Category> getCategoriesWithNews() {
        List<News> allNews = newsBO.getAllNews();
        return getAllCategories().stream()
                .filter(c -> allNews.stream().anyMatch(n -> n.getCategoryId() == c.getId()))
                .collect(Collectors.toList());
    }

    // Поиск категорий по ключевым словам
    public List<Category> searchByKeywords(String keyword) {
        return getAllCategories().stream()
                .filter(c -> c.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
