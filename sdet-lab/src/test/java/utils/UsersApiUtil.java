package utils;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UsersApiUtil {

    public static ValidatableResponse getUser(int id) {
        return given()
                // ЖЁСТКО задаём адрес и путь, чтобы не влияли глобальные настройки
                .baseUri("https://reqres.in")
                .basePath("/api")
                .relaxedHTTPSValidation()
                .header("Accept", "application/json")
                .pathParam("id", id)
                .log().all()                // временно: лог запроса
                .when()
                .get("/users/{id}")
                .then()
                .log().all()                // временно: лог ответа
                .statusCode(200);
    }
}
