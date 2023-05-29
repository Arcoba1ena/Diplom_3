package api.request;

import io.restassured.RestAssured;

public class Request {
    public void apiEndPoint() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api";
    }
}