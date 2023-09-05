package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import exercise.repository.ArticleRepository;
import exercise.model.Article;
import java.util.List;

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
        ArticleRepository.clear();
    }

    @Test
    void testRootPage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testListArticles() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/articles").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testNewArticlePage() throws Exception {
        HttpResponse<String> response = Unirest.get(baseUrl + "/articles/new").asString();
        assertEquals(200, response.getStatus());
    }

    @Test
    void testCreateArticlePositive() throws Exception {
        HttpResponse responsePost = Unirest
            .post(baseUrl + "/articles")
            .field("title", "test title")
            .field("content", "test content")
            .asEmpty();

        assertEquals(302, responsePost.getStatus());
        assertEquals("/articles", responsePost.getHeaders().getFirst("Location"));

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/articles")
            .asString();
        String body = response.getBody();
        assertThat(body, containsString("test title"));
        assertThat(body, containsString("test content"));

        var article = ArticleRepository.findByTitle("test title");
        assertThat(article, is(notNullValue()));
    }

    @Test
    void testCreateArticleNegative1() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/articles")
            .field("title", "testTitle")
            .field("content", "testBody")
            .asString();

        assertEquals(422, responsePost.getStatus());

        String body = responsePost.getBody();
        assertThat(body, containsString("testTitle"));
        assertThat(body, containsString("testBody"));
        assertThat(body, containsString("Статья должна быть не короче 10 символов"));

        var article = ArticleRepository.findByTitle("test title");
        assertNull(article);
    }

    @Test
    void testCreateArticleNegative2() throws Exception {
        HttpResponse<String> responsePost = Unirest
            .post(baseUrl + "/articles")
            .field("title", "q")
            .field("content", "test content")
            .asString();

        assertEquals(422, responsePost.getStatus());

        String body = responsePost.getBody();
        assertThat(body, containsString("q"));
        assertThat(body, containsString("test content"));
        assertThat(body, containsString("Название не должно быть короче двух символов"));

        var article = ArticleRepository.findByTitle("q");
        assertNull(article);
    }

    @Test
    void testCreateArticleNegative3() throws Exception {
        HttpResponse<String> response1 = Unirest
            .post(baseUrl + "/articles")
            .field("title", "test article")
            .field("content", "test content")
            .asEmpty();

        assertEquals(302, response1.getStatus());

        HttpResponse<String> response2 = Unirest
            .post(baseUrl + "/articles")
            .field("title", "test article")
            .field("content", "test content 2")
            .asString();

        assertEquals(422, response2.getStatus());

        String body = response2.getBody();
        assertThat(body, containsString("test article"));
        assertThat(body, containsString("test content 2"));
        assertThat(body, containsString("Статья с таким названием уже существует"));

        List<Article> articles = ArticleRepository.search("test article");
        assertEquals(1, articles.size());
    }
}
