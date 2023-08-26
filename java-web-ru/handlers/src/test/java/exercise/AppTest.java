package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
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

    @Test
    void testDomains() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> expected = List.of(
            "durgan.biz",
            "hansen.info",
            "runte.net",
            "mclaughlin.info",
            "ziemann.net",
            "smitham.net",
            "mills.biz",
            "wolf.org",
            "wisoky.info",
            "rosenbaum.biz"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/domains").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testPhones() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> expected = List.of(
            "760.838.2141",
            "(032) 534-5682",
            "046-308-0640",
            "312-817-9390",
            "(887) 008-9456",
            "567.055.5993",
            "614.541.6357",
            "190-080-7706",
            "400.646.8534",
            "912.751.7658"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/phones").asString();
        String content = response.getBody();
        List<String> actual = mapper.readValue(content, new TypeReference<List<String>>() { });
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }
}
