package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SmokeApiTest {

    @Test
    public void post_users_minimal_201() {
        // Полный сброс любых глобальных настроек и фильтров
        RestAssured.reset();

        // На всякий случай — убираем системные прокси
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");

        given()
                .baseUri("https://reqres.in")
                .basePath("/api")
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .log().all()
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }
}