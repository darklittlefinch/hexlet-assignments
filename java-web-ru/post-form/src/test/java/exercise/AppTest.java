package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import exercise.repository.UserRepository;
import exercise.util.Security;

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

    @BeforeEach
    public void clear() {
        UserRepository.clear();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testListUsers() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users").asString();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testBuildUserPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/users/build").asString();

        assertEquals(200, response.getStatus());
    }

    @Test
    void testRegisterNewUser1() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/users")
            .field("firstName", "test")
            .field("lastName", "user")
            .field("email", "  User@gmail.com  ")
            .field("password", "12345678")
            .asEmpty();

        assertEquals(302, responsePost.getStatus());
        assertEquals("/users", responsePost.getHeaders().getFirst("Location"));

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        String body = response.getBody();
        assertThat(body, containsString("Test User"));

        var user = UserRepository.search("Test").get(0);
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertEquals("user@gmail.com", user.getEmail());
        assertEquals(Security.encrypt("12345678"), user.getPassword());
    }
}
