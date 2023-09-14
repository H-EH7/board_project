package eh7.board.web.post;

import eh7.board.SessionConst;
import eh7.board.domain.member.Member;
import eh7.board.domain.post.Post;
import eh7.board.domain.post.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        loginCheck(member, model);

        Optional<Post> findResult = postService.findPostById(id);

        if (findResult.isEmpty()) {
            return "redirect:/board";
        }

        Post post = findResult.get();

        model.addAttribute("post", post);

        return "post";
    }

    @PostMapping("/post")
    public String post(@Valid PostForm postForm,
                       BindingResult bindingResult,
                       HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "write";
        }

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        Post post = postService.post(new Post(member.getId(), member.getName(), postForm.getTitle(), postForm.getContent()));

        redirectAttributes.addAttribute("id", post.getId());

        return "redirect:post/{id}";
    }

    private void loginCheck(Member member, Model model) {
        boolean isLogin = false;

        if (member != null) {
            isLogin = true;
        }

        model.addAttribute("isLogin", isLogin);
    }
}
