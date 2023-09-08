package eh7.board.domain.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long id;
    private Long userId;
    private String writer;
    private String title;
    private LocalDateTime postDate;
    private String content;
    private Long views;

    public Post(Long userId, String writer, String title, String content) {
        this.userId = userId;
        this.writer = writer;
        this.title = title;
        this.postDate = LocalDateTime.now();
        this.content = content;
        this.views = 0L;
    }
}
