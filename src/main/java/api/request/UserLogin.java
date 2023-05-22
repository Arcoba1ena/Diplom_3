package api.request;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserLogin extends Request{
    public String requestUserLogin(Object model, Integer code) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(model)
                .when()
                .post("/auth/login");
        response.then().statusCode(code);
        return response.getBody().asString();
    }

    public String requestUserLogin() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .post("/auth/login");
        response.then().statusCode(401);
        return response.getBody().asString();
    }
}