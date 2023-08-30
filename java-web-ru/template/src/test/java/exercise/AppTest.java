package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        String content = response.getBody();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testListUsers() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();
        String content = response.getBody();

        assertEquals(200, response.getStatus());
        assertThat(content, containsString("Bobbi Wehner"));
        assertThat(content, containsString("Will Casper"));
        assertThat(content, containsString("Darryl Ward"));
    }

    @Test
    void testShowUser1() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users/1").asString();
        String content = response.getBody();
        assertEquals(200, response.getStatus());
        assertThat(content, containsString("Pearl Schultz"));
        assertThat(content, is(not(containsString("Bobbi Wehner"))));
    }

    @Test
    void testShowUser2() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users/5").asString();
        String content = response.getBody();
        assertEquals(200, response.getStatus());
        assertThat(content, containsString("Minerva Altenwerth"));
        assertThat(content, is(not(containsString("Ilse Roob"))));
    }
}
