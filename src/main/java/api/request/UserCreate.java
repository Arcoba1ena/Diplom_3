package api.request;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCreate extends Request{
    public String requestUserCreate(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/auth/register");
        response.then().statusCode(code);
        return response.getBody().asString();
    }
}