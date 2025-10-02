package api;

import api.models.UsersListResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersApiUtilTest extends BaseApiTest {

    @Test
    public void listUsers_page2_hasSixUsers() {
        given(specWithKey)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .body("page", equalTo(2))
                .body("data", hasSize(6))
                .body("data[0].id", notNullValue());
    }

    @DataProvider
    public Object[][] existingUserIds() {
        return new Object[][]{{1}, {2}, {3}};
    }

    @Test(dataProvider = "existingUserIds")
    public void getUser_existing_returns200(int id) {
        given(specWithKey)
                .pathParam("id", id)
                .when()
                .get("/users/{id}")
                .then()
                .spec(resp200)
                .body("data.id", equalTo(id))
                .body("data.email", containsString("@"));
    }

    @Test
    public void getUser_notFound_returns404() {
        given(specWithKey)
                .pathParam("id", 23)
                .when()
                .get("/users/{id}")
                .then().statusCode(404);

    }

    @Test
    public void listUsers_withDelay_still200() {
        given(specWithKey)
                .queryParam("delay", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .body("data.size()", greaterThan(0));
    }

    @Test
    public void listUsers_schemaValid() {
        given(specWithKey)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .body(matchesJsonSchemaInClasspath("schema/users-list-schema.json"));
    }

    @Test
    public void listUsers_withoutApiKey_returns401() {
        given(specWithoutKey)
                .queryParam("page", 2)
                .queryParam("_", System.currentTimeMillis())   // кэш-бастер
                .header("Cache-Control", "no-cache, no-store, max-age=0")
                .header("Pragma", "no-cache")
                .when()
                .get("/users")
                .then()
                .spec(resp401)
                .body("error", equalTo("Missing API key"));
    }

    @Test
    public void listUsers_headers_and_latency() {
        given(specWithKey)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .header("Content-Type", containsString("application/json"))
                .header("Ratelimit-Limit", not(isEmptyOrNullString()))
                .time(lessThan(2000L)); // до 2 сек
    }

    @Test
    public void listUsers_deserialize_and_assert() {
        UsersListResponse resp =
                given(specWithKey).queryParam("page", 2)
                        .when().get("/users")
                        .then().spec(resp200)
                        .extract().as(UsersListResponse.class);

        // было: resp.page / resp.data / u.email
        Assert.assertEquals(resp.getPage(), 2);
        assertThat(resp.getData(), hasSize(6));
        assertThat(resp.getData().get(0).getEmail(), not(isEmptyOrNullString()));
    }

    @Test
    public void listUsers_outOfRange_returnsEmptyOr404() {
        var resp = given(specWithKey)
                .queryParam("page", 9999)
                .when()
                .get("/users")
                .then()
                .extract();

        int code = resp.statusCode();
        if (code == 200) {
            resp.body().jsonPath().getList("data");
            assertThat(resp.body().jsonPath().getList("data").size(), is(0));
        } else {
            assertThat(code, is(404));
        }
    }

    @Test
    public void createUser_contract() {
        String body = """
                {
                  "name": "morpheus",
                  "job": "leader"
                }
                """;

        given(specWithKey)
                .baseUri("https://reqres.in")   // дублируем для чистоты
                .basePath("/api")
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(resp201); // ожидаем 201

        // при желании добавь проверки схемы/полей:
        // .body("id", notNullValue())
        // .body("createdAt", notNullValue());
    }
}
