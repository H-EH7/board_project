package eh7.board.web.post;

import eh7.board.SessionConst;
import eh7.board.domain.member.Member;
import eh7.board.domain.post.Post;
import eh7.board.domain.post.PostService;
import eh7.board.domain.view.ViewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ViewService viewService;

    @GetMapping("/write")
    public String writePage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                            @ModelAttribute PostForm postForm,
                            Model model) {

        loginCheck(member, model);

        return "write";
    }

    @GetMapping("/post/{id}")
    public String getPost(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                          @PathVariable Long id,
                          Model model) {
        // 로그인 유무 확인
        boolean isLogin = loginCheck(member, model);

        // 작성자 확인
        boolean isWriter = false;
        if (isLogin) {
            isWriter = postService.writerCheck(id, member.getId());
        }
        model.addAttribute("isWriter", isWriter);

        // 조회수 올리기
        if (isLogin) {
            viewService.view(id, member.getId());
        }

        // 게시글 존재하는지 확인
        Optional<Post> findResult = postService.findPostById(id);
        if (findResult.isEmpty()) {
            return "redirect:/board";
        }

        Post post = findResult.get();
        model.addAttribute("post", post);

        return "post";
    }

    @GetMapping("/post/{id}/edit")
    public String editPost(PostForm postForm,
                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                           @PathVariable Long id,
                           Model model) {
        loginCheck(member, model);

        model.addAttribute("postId", id);
        return "editPost";
    }

    @PostMapping("/post")
    public String post(@Valid PostForm postForm,
                       BindingResult bindingResult,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "write";
        }

        Post post = postService.post(new Post(member.getId(), member.getName(), postForm.getTitle(), postForm.getContent()));

        redirectAttributes.addAttribute("id", post.getId());

        return "redirect:post/{id}";
    }

    @PostMapping("/post/{id}")
    public String updatePost(@Valid PostForm postForm,
                             BindingResult bindingResult,
                             @PathVariable Long id,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            return "editPost";
        }

        postService.update(id, postForm);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/post/{id}";
    }

    @PostMapping("/post/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/board";
    }

    private boolean loginCheck(Member member, Model model) {
        boolean isLogin = false;

        if (member != null) {
            isLogin = true;
        }

        model.addAttribute("isLogin", isLogin);

        return isLogin;
    }
}
