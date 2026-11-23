package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Глобальное хранилище новостей. Реализовано по паттерну Singleton:
 * экземпляр можно получить только через getInstance().
 */
public class NewsStorage {

    private static final NewsStorage INSTANCE = new NewsStorage();

    private final Map<Integer, News> newsMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    private NewsStorage() {}

    public static NewsStorage getInstance() {
        return INSTANCE;
    }

    public News add(String title, String text) {
        int id = idCounter.incrementAndGet();
        News news = new News(id, title, text);
        newsMap.put(id, news);
        return news;
    }

    public News getById(int id) {
        return newsMap.get(id);
    }

    public List<News> getAll() {
        return new ArrayList<>(newsMap.values());
    }
}
