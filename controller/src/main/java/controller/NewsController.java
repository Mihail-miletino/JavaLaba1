package controller;

import model.News;
import model.NewsStorage;

import java.util.List;

public class NewsController {

    private final NewsStorage storage = NewsStorage.getInstance();

    public News create(String title, String text) {
        return storage.add(title, text);
    }

    public News getById(int id) {
        return storage.getById(id);
    }

    public List<News> getAll() {
        return storage.getAll();
    }
}
