// src/test/java/api/UsersApiTest.java
package api;

import api.models.UsersListResponse;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersApiTest extends BaseApiTest {

    @Test
    public void listUsers_page2_hasSixUsers() {
        given()
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
    public Object[][] existingUserIds() { return new Object[][]{{1},{2},{3}}; }

    @Test(dataProvider = "existingUserIds")
    public void getUser_existing_returns200(int id) {
        given()
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
        given()
                .pathParam("id", 23)
                .when()
                .get("/users/{id}")
                .then()
                .spec(resp404);
    }

    @Test
    public void listUsers_withDelay_still200() {
        given()
                .queryParam("delay", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .body("data.size()", greaterThan(0));
    }

    @Test
    public void listUsers_schemaValid() {
        given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .spec(resp200)
                .body(matchesJsonSchemaInClasspath("schema/users-list-schema.json"));
    }

    @Test
    public void listUsers_withoutApiKey_returns401or403() {
        given()
                .queryParam("page", 2)
                .header("x-api-key", (Object) null)  // перезаписываем/убираем заголовок
                .when()
                .get("/users")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

    @Test
    public void listUsers_withoutApiKey_returns401or403_alt() {
        given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri("https://reqres.in")
                        .setBasePath("/api")
                        .build())
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

    @Test
    public void listUsers_headers_and_latency() {
        given()
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
                given()
                        .queryParam("page", 2)
                        .when()
                        .get("/users")
                        .then()
                        .spec(resp200)
                        .extract().as(UsersListResponse.class);

        Assert.assertEquals(resp.page, 2, "Page mismatch");
        Assert.assertEquals(resp.data.size(), 6, "Expected 6 users");
        Assert.assertTrue(resp.data.stream().allMatch(u -> u.email.contains("@")), "All emails must contain @");
    }

    @Test
    public void listUsers_outOfRange_returnsEmptyOr404() {
        var resp = given()
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
      { "name": "morpheus", "job": "leader" }
    """;

        given()
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(anyOf(is(201), is(200)))
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .body("$", hasKey("id"))
                .body("$", anyOf(hasKey("createdAt"), hasKey("updatedAt")));
    }


}
