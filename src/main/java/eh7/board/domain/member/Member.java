package eh7.board.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String userId;
    private String name;
    private String password;

    public Member(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }
}
