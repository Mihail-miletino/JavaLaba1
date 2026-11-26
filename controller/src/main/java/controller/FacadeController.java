package controller;

import controller.category.CategoryBO;
import controller.news.NewsBO;
import model.Category;
import model.News;

import java.util.ArrayList;
import java.util.List;

public class FacadeController {
    private final NewsBO newsBO = new NewsBO();
    private final CategoryBO categoryBO = new CategoryBO();

    // --- Делегирование методов для API (CRUD) ---
    public List<News> getAllNews() { return newsBO.getAllNews(); }
    public News addNews(String title, String text, int categoryId) { return newsBO.addNews(title, text, categoryId); }
    public News getNewsById(int id) { return newsBO.getNewsById(id); }

    public List<Category> getAllCategories() { return categoryBO.getAllCategories(); }
    public Category addCategory(String name) { return categoryBO.addCategory(name); }
    public List<Category> getCategoriesWithNews() { return categoryBO.getCategoriesWithNews(); }

    // --- Сложный алгоритм со Слайда 16 ---
    // Алгоритм поиска новости по ключевым словам
    public List<News> searchNewsByKeywords(String keyword) {
        List<News> targetNews = new ArrayList<>();

        // 1 и 2. Получить список БО Новость по ключевым словам
        List<News> newsByKeyword = newsBO.searchByKeywords(keyword);
        targetNews.addAll(newsByKeyword);

        // 3. Получить список БО Категория по ключевым словам
        List<Category> categoriesByKeyword = categoryBO.searchByKeywords(keyword);

        // 4. Извлечь из категорий все БО Новость (исключить повторы)
        for (Category category : categoriesByKeyword) {
            List<News> newsInCategory = newsBO.getAllNews().stream()
                    .filter(n -> n.getCategoryId() == category.getId())
                    .toList();

            for (News n : newsInCategory) {
                // 5. Добавить в целевой список все найденные новости (исключить повторы)
                if (targetNews.stream().noneMatch(existing -> existing.getId() == n.getId())) {
                    targetNews.add(n);
                }
            }
        }

        // 6. Вернуть сформированный список (возвращаем BO, API сам переведёт в DTO)
        return targetNews;
    }
}
