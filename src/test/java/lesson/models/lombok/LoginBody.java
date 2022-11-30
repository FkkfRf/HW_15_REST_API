package lesson.models.lombok;
import lombok.Data;

@Data
public class LoginBody {
    //  "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
    private String email, password;
}
