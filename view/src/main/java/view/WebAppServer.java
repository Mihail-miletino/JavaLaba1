package view;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import view.api.WebAppSingletons;

public class WebAppServer {

    /**
     * Запускает веб-сервер. По окончании работы требуется ручная остановка процесса.
     */
    public static void main(String[] argv) {
        startWebServer();
    }

    private static void startWebServer() {
        Undertow.Builder builder = Undertow.builder().addHttpListener(8096, "localhost");
        UndertowJaxrsServer server = new UndertowJaxrsServer().start(builder);
        server.deploy(WebAppSingletons.class);
        System.out.println("Сервер запущен: http://localhost:8096/");
    }
}
