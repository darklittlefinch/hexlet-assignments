package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import static org.assertj.core.api.Assertions.assertThat;
import org.eclipse.jetty.server.Server;

class AppTest {

    private static int port;
    private static String hostname = "localhost";
    private static Server app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() throws Exception {
        app = App.getApp(0);
        app.start();
        port = app.getURI().getPort();
        baseUrl = "http://localhost:" + port;
    }

    @Test
    void testGreeting1() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl)
            .asString();
        String body = response.getBody();
        assertThat(body).contains("Guest");
    }

    @Test
    void testGreeting2() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "?name=John")
            .asString();
        String body = response.getBody();
        assertThat(body).contains("John");
    }

    @Test
    void testGreeting3() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "?name=World")
            .asString();
        String body = response.getBody();
        assertThat(body).contains("World");
    }

    @AfterAll
    public static void afterAll() throws Exception {
        app.stop();
    }
}
