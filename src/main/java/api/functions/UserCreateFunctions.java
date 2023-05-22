package api.functions;

import api.models.request.UserCreateRequestModel;
import api.request.UserCreate;

public class UserCreateFunctions extends UserCreate {
    public String getUserCreate(String name, String email, String password, Integer code) {
        UserCreateRequestModel requestModel = new UserCreateRequestModel(name, email, password);
        return requestUserCreate(requestModel, code);
    }
}
