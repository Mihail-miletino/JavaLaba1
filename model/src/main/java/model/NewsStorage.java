package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class NewsStorage {
    private static final NewsStorage INSTANCE = new NewsStorage();
    private final Map<Integer, News> newsMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    private NewsStorage() {}
    public static NewsStorage getInstance() { return INSTANCE; }

    public News add(String title, String text, int categoryId) {
        int id = idCounter.incrementAndGet();
        News news = new News(id, title, text, "", LocalDateTime.now(), 0, categoryId);
        newsMap.put(id, news);
        return news;
    }

    public News getById(int id) { return newsMap.get(id); }
    public List<News> getAll() { return new ArrayList<>(newsMap.values()); }

    public boolean delete(int id) {
        return newsMap.remove(id) != null;
    }

    public News update(int id, String title, String text) {
        News news = newsMap.get(id);
        if (news != null) {
            news.setTitle(title);
            news.setText(text);
            return news;
        }
        return null;
    }
}
