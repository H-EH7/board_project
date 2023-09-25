package eh7.board.domain.post;

import eh7.board.domain.member.Member;
import eh7.board.web.post.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post post(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
    public List<Post> findByPageNum(Integer start, Integer total) {
        return postRepository.findByPage(start, total);
    }

    public void update(Long id, PostForm postForm) {
        postRepository.update(id, postForm);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }

    public int getTotalPosts() {
        return postRepository.getTotalContents();
    }

    public boolean writerCheck(Long postId, Long memberId) {
        return postRepository.findById(postId)
                .filter(post -> post.getUserId() == memberId)
                .isPresent();
    }
}
