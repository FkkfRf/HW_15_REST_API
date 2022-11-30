package rest.models;

import lombok.Data;

@Data
public class RegisterBody {
    //String requestBody = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";
    private String email, password;
}
