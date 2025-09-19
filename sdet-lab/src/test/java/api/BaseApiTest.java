// src/test/java/api/BaseApiTest.java
package api;

import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.filter.log.*;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {
    protected static RequestSpecification reqSpec;
    protected static ResponseSpecification resp200;
    protected static ResponseSpecification resp404;

    @BeforeClass
    public void baseSetup() {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .addHeader("x-api-key", "reqres-free-v1") // твой ключ остаётся
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        resp200 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        resp404 = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();

        RestAssured.requestSpecification = reqSpec;
    }
}

