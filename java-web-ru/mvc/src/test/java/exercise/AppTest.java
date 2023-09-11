package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import exercise.repository.PostRepository;
import exercise.model.Post;

class AppTest {

    private static Javalin app;
    private static String baseUrl;
    private static Post existingPost;

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
        PostRepository.clear();
        existingPost = new Post("test name", "test body 1");
        PostRepository.save(existingPost);
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testListPosts() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testBuildPost() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/posts/build").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testCreatePost() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/posts")
            .field("name", "test name 2")
            .field("body", "test body 2")
            .asEmpty();

        assertEquals(302, responsePost.getStatus());
        assertEquals("/posts", responsePost.getHeaders().getFirst("Location"));

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts")
            .asString();
        String body = response.getBody();
        assertThat(body, containsString("test name 2"));
        assertThat(body, containsString("test body 2"));

        var post = PostRepository.findByName("test name 2");
        assertThat(post, is(notNullValue()));
    }

    @Test
    void testShowPost() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId())
            .asString();
        String body = response.getBody();
        assertThat(body, containsString(existingPost.getName()));
        assertThat(body, containsString(existingPost.getBody()));
        assertThat(body, containsString("posts/" + existingPost.getId() + "/edit"));
    }

    @Test
    void testCreatePostNegative() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/posts")
            .field("name", "test name")
            .field("body", "test")
            .asEmpty();

        assertEquals(422, responsePost.getStatus());
    }

    @Test
    void testEditPost() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId() + "/edit").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testUpdatePost() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/posts/" + existingPost.getId())
            .field("name", "new name")
            .field("body", "test content")
            .asString();

        assertEquals(302, responsePost.getStatus());
        assertEquals(true, PostRepository.existsByName("new name"));
    }

    @Test
    void testUpdatePostNegative() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/posts/" + existingPost.getId())
            .field("name", "q")
            .field("body", "test content")
            .asString();

        assertEquals(422, responsePost.getStatus());

        String body = responsePost.getBody();
        assertThat(body, containsString("q"));
        assertThat(body, containsString("test content"));

        assertEquals(false, PostRepository.existsByName("q"));
    }

    @Test
    void testPostNotFound() throws Exception {
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/posts/" + existingPost.getId() + 1 + "/edit").asString();
        assertEquals(500, response.getStatus());
    }
}
