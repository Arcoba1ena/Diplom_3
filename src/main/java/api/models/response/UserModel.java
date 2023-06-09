package api.models.response;

public class UserModel {
    public String name;
    public String email;

    public UserModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}