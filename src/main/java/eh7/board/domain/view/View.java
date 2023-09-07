package eh7.board.domain.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class View {
    private Long id;
    private Long postId;
    private Long userId;

    public View(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
