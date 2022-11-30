package rest;

import org.junit.jupiter.api.Test;
import rest.models.RegisterBody;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresHWorkModelTest {
    public String register = "https://reqres.in/api/register";

    public String users = "https://reqres.in/api/users";

    @Test
    void registerSuccessTest() {
        RegisterBody registerBody = new RegisterBody();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");

        given()
                .log().uri().body(registerBody).contentType(JSON)
                .when()
                .post(register)
                .then()
                .statusCode(200)
                .log().status()
                .body("id", is(4));
    }

    @Test
    void registerUnSuccessTest() {
        String requestBody = "{\"email\": \"sydney@fife\"}";
        given()
                .log().uri().body(requestBody).contentType(JSON)
                .when()
                .post(register)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createSuccessTest() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .log().uri()
                .when()
                .body(data).contentType(JSON)
                .post(users)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void createUnSuccessTest() {
        given()
                .log().uri()
                .when()
                .contentType(JSON)
                .body("data")
                .post(users)
                .then()
                .log().status()
                .log().body()
                .statusCode(400);

    }

    @Test
    void deleteSuccessTest() {
        given()
                .log().uri()
                .when()
                .delete(users)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void singleUserSuccessTest() {
        given()
                .log().uri()
                .when()
                .contentType(JSON)
                .get(users)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(1));
    }
}

