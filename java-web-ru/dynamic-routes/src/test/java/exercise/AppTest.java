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
import java.util.Map;


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
    void testCompany1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> expected = Map.of(
            "name", "Morar-Wehner", "phone", "(325) 345-6823", "id", "6"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/companies/6").asString();
        String content = response.getBody();
        Map<String, String> actual = mapper.readValue(
            content, new TypeReference<Map<String, String>>() { }
        );
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testCompany2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> expected = Map.of(
            "name", "Schamberger Inc", "phone", "(502) 173-0262", "id", "30"
        );

        HttpResponse<String> response = Unirest.get(baseUrl + "/companies/30").asString();
        String content = response.getBody();
        Map<String, String> actual = mapper.readValue(
            content, new TypeReference<Map<String, String>>() { }
        );
        assertEquals(200, response.getStatus());
        assertEquals(expected, actual);
    }

    @Test
    void testCompanyNotFound() throws Exception {

        HttpResponse<String> response = Unirest.get(baseUrl + "/companies/999").asString();
        String content = response.getBody();
        assertEquals(404, response.getStatus());
        assertEquals("Company not found", content);
    }

}
