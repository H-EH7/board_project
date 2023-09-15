package eh7.board.domain.view;

import eh7.board.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewService {
    private final ViewRepository viewRepository;
    private final PostRepository postRepository;

    @Transactional
    public void view(Long postId, Long memberId) {
        boolean alreadyView = viewRepository.findByPostId(postId).stream()
                .filter(view -> view.getUserId() == memberId)
                .findAny()
                .isPresent();

        if (alreadyView) return;

        View view = new View(postId, memberId);
        viewRepository.save(view);
        postRepository.updateView(postId);
    }
}
