package eh7.board.domain.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    public Member(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
}
