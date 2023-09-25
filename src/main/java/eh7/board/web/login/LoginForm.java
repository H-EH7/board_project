package eh7.board.web.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;
}
