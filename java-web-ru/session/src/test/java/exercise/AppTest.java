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

    @Test
    void testLoginPass() throws Exception {
        HttpResponse<String> response1 = Unirest.get(baseUrl + "/").asString();
        assertEquals(200, response1.getStatus());
        var body1 = response1.getBody();
        assertThat(body1, containsString("Войти"));
        assertThat(body1, is(not(containsString("Выйти"))));

        HttpResponse<String> response2 = Unirest
            .get(baseUrl + "/sessions/build").asString();
        assertEquals(200, response2.getStatus());

        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/sessions")
            .field("name", "admin")
            .field("password", "secret")
            .asEmpty();

        assertEquals(302, responsePost.getStatus());

        HttpResponse<String> response3 = Unirest.get(baseUrl + "/").asString();
        var body3 = response3.getBody();
        assertEquals(200, response3.getStatus());
        assertThat(body3, containsString("Выйти"));
        assertThat(body3, is(not(containsString("Войти"))));
        assertThat(body3, containsString("admin"));

        HttpResponse<String> responseDelete = Unirest
            .post(baseUrl + "/sessions/delete")
            .asEmpty();
        assertEquals(302, responseDelete.getStatus());

        HttpResponse<String> response4 = Unirest.get(baseUrl + "/").asString();
        var body4 = response4.getBody();
        assertThat(body4, containsString("Войти"));
        assertThat(body4, is(not(containsString("Выйти"))));
    }

    @Test
    void testLoginFail1() throws Exception {

        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/sessions")
            .field("name", "noname")
            .field("password", "secret")
            .asString();

        var body = responsePost.getBody();
        assertThat(body, containsString("Wrong"));
    }

    @Test
    void testLoginFail2() throws Exception {

        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/sessions")
            .field("name", "admin")
            .field("password", "wrong_password")
            .asString();

        var body = responsePost.getBody();
        assertThat(body, containsString("Wrong"));
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
}
