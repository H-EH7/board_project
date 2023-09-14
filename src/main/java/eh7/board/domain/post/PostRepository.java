package eh7.board.domain.post;

import java.util.List;
import java.util.Optional;

/**
 * 게시글 저장소 인터페이스
 */
public interface PostRepository {
    /**
     * 게시글 저장
     * @param post
     * @return
     */
    Post save(Post post);

    /**
     * 게시글 조회
     * @param id
     * @return
     */
    Optional<Post> findById(Long id);

    /**
     * 모든 게시글 조회
     * @return
     */
    List<Post> findAll();
}
