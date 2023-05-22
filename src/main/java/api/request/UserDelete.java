package api.request;

import static io.restassured.RestAssured.given;

public class UserDelete extends Request{
    public static void requestDelete(String token) {
        given().auth()
                .oauth2(token.replace("Bearer ", ""))
                .header("Content-type", "application/json")
                .when()
                .delete("/auth/user")
                .then().statusCode(202);
    }

    public static void requestDelete() {
        given()
                .header("Content-type", "application/json")
                .when()
                .delete("/auth/user")
                .then().statusCode(401);
    }
}