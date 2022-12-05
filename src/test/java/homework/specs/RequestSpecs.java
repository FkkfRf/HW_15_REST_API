package homework.specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {

    public static RequestSpecification registerRequestSpec = with()
            //.filter(withCustomTemplates())
            .basePath("/api/register")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification usersRequestSpec = with()
            //.filter(withCustomTemplates())
            .basePath("/api/users")
            .log().all()
            .contentType(JSON);
}
