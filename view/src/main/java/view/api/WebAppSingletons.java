package view.api;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class WebAppSingletons extends Application {
    private final Set<Object> singletons = new HashSet<>();

    public WebAppSingletons() {
        singletons.add(new NewsAPI());
        singletons.add(new CategoryAPI());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
