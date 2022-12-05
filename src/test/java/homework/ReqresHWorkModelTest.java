package homework;

import homework.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static homework.specs.RequestSpecs.registerRequestSpec;
import static homework.specs.RequestSpecs.usersRequestSpec;
import static homework.specs.ResponseSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresHWorkModelTest extends BaseTest {
    @Test
    @DisplayName("Проверка успешной регистрации")
    void registerSuccessTest() {
        RegisterBody registerBody = new RegisterBody();
        registerBody.setEmail("eve.holt@reqres.in");
        registerBody.setPassword("pistol");

        RegisterSuccessResponse response = given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post()
                .then()
                .spec(registerSuccessResponseSpec)
                .extract().as(RegisterSuccessResponse.class);

        assertThat(response.getId()).isEqualTo(4);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Проверка НЕуспешной регистрации")
    void registerUnSuccessTest() {
        RegisterBody registerBody = new RegisterBody();
        registerBody.setEmail("sydney@fife");

        RegisterUnSuccessResponse response = given()
                .spec(registerRequestSpec)
                .body(registerBody)
                .when()
                .post()
                .then()
                .spec(registerUnSuccessResponseSpec)
                .extract().as(RegisterUnSuccessResponse.class);
        assertThat(response.getError()).isEqualTo("Missing password");
    }

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    void createSuccessTest() {
        CreateBody createBody = new CreateBody();
        createBody.setJob("leader");
        createBody.setName("morpheus");

        CreateSuccessResponse response = given()
                .spec(usersRequestSpec)
                .body(createBody)
                .when()
                .post()
                .then()
                .spec(createSuccessResponseSpec)
                .extract().as(CreateSuccessResponse.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @Test
    @DisplayName("Проверка НЕуспешного создания пользователя")
    void createUnSuccessTest() {
        given()
                .spec(usersRequestSpec)
                .body("TestUser")
                .when()
                .post()
                .then()
                .spec(createUnSuccessResponseSpec);
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteSuccessTest() {
        given()
                .spec(usersRequestSpec)
                .when()
                .delete()
                .then()
                .spec(deleteResponseSpec);
    }
}

