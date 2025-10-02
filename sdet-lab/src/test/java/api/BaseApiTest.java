package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseApiTest {

    protected ResponseSpecification resp200;
    protected ResponseSpecification resp201;
    protected ResponseSpecification resp401;
    protected ResponseSpecification resp404;

    // две спеки вместо одной
    protected RequestSpecification specWithKey;
    protected RequestSpecification specWithoutKey;

    @BeforeClass
    public void setup() {
        baseURI = "https://reqres.in";
        basePath = "/api";

        String apiKey = System.getProperty("apiKey",
                System.getenv().getOrDefault("API_KEY", "reqres-free-v1"));

        // спецификация С ключом
        specWithKey = new RequestSpecBuilder()
                .addHeader("x-api-key", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // спецификация БЕЗ ключа
        specWithoutKey = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        // ⚠️ не задавай requestSpecification глобально,
        // иначе она всегда будет использовать specWithKey
        // requestSpecification = specWithKey;

        resp200 = new ResponseSpecBuilder().expectStatusCode(200).build();
        resp201 = new ResponseSpecBuilder().expectStatusCode(201).build();
        resp401 = new ResponseSpecBuilder().expectStatusCode(401).build();
        resp404 = new ResponseSpecBuilder().expectStatusCode(404).build();
    }
}
