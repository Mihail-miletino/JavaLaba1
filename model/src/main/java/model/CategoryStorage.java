package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryStorage {
    private static final CategoryStorage INSTANCE = new CategoryStorage();
    private final Map<Integer, Category> categoryMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    private CategoryStorage() {}
    public static CategoryStorage getInstance() { return INSTANCE; }

    public Category add(String name) {
        int id = idCounter.incrementAndGet();
        Category category = new Category(id, name);
        categoryMap.put(id, category);
        return category;
    }
    public Category getById(int id) { return categoryMap.get(id); }
    public List<Category> getAll() { return new ArrayList<>(categoryMap.values()); }
}
