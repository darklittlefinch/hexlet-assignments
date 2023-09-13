package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
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
    void testCreateUser() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertEquals(200, response.getStatus());

        HttpResponse<String> responseUser1 = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "First Name")
            .field("lastName", "Last Name")
            .field("email", "some@domain.net")
            .field("password", "password")
            .asEmpty();

        assertEquals(302, responseUser1.getStatus());
        assertEquals("/users/1", responseUser1.getHeaders().getFirst("Location"));

        HttpResponse<String> responseUser2 = Unirest
            .get(baseUrl + "/users/1")
            .asString();

        String body1 = responseUser2.getBody();
        assertThat(body1, containsString("First Name"));
        assertThat(body1, containsString("Last Name"));
        assertThat(body1, containsString("some@domain.net"));

        HttpResponse<String> responseUser3 = Unirest
            .get(baseUrl + "/users/2")
            .asString();

        HttpResponse<String> responseUser4 = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "First Name1")
            .field("lastName", "Last Name1")
            .field("email", "some1@domain.net")
            .field("password", "password")
            .asEmpty();

        assertEquals(302, responseUser4.getStatus());
        assertEquals("/users/2", responseUser4.getHeaders().getFirst("Location"));

        HttpResponse<String> responseUser5 = Unirest
            .get(baseUrl + "/users/2")
            .asString();

        String body2 = responseUser5.getBody();
        assertThat(body2, containsString("First Name1"));
        assertThat(body2, containsString("Last Name1"));
        assertThat(body2, containsString("some1@domain.net"));

        HttpResponse<String> responseUser6 = Unirest
            .get(baseUrl + "/users/1")
            .asString();

        String body3 = responseUser6.getBody();
        assertThat(body3, not(containsString("First Name")));
        assertThat(body3, not(containsString("Last Name")));
        assertThat(body3, not(containsString("some@domain.net")));
    }
}
