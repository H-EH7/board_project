package eh7.board.domain.view;

import java.util.List;

public interface ViewRepository {
    void save(View view);

    List<View> findByPostId(Long postId);
}
