package eh7.board.web.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PostForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
