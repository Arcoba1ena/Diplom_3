package api.functions;

import api.request.UserCreate;
import api.models.request.UserCreateRequestModel;

public class UserCreateFunctions extends UserCreate {
    public String getUserCreate(String name, String email, String password, Integer code) {
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);
        return requestUserCreate(requestModel, code);
    }
}