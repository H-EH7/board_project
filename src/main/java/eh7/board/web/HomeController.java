package eh7.board.web;

import eh7.board.SessionConst;
import eh7.board.domain.member.Member;
import eh7.board.domain.post.Post;
import eh7.board.domain.post.PostService;
import eh7.board.web.page.Page;
import eh7.board.web.page.PageConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;

    @GetMapping
    public String home() {
        return "redirect:board?page=1";
    }

    @GetMapping("/board")
    public String boardPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                            @RequestParam(name = "page") Integer pageNum,
                            Model model) {

        loginCheck(member, model);

        // 페이징 처리 ================================================
        if (pageNum < 1) pageNum = 1; // 페이지 번호 보정

        int totalPosts = postService.getTotalPosts();
        int totalPages = (totalPosts / PageConst.PAGE_SIZE) + (totalPosts % PageConst.PAGE_SIZE > 0 ? 1 : 0);
        // 게시글이 하나도 없을 경우
        if (totalPages == 0) {
            model.addAttribute("page", new Page(1, 1, 0, 1, 1, 1, false, false));
            return "board";
        }
        if (pageNum > totalPages) pageNum = totalPages; // 페이지 번호 보정
        
        // 페이지 네비바를 위한 로직
        boolean hasPrev = pageNum > 1;
        boolean hasNext = pageNum < totalPages;
        int navSize = PageConst.MAX_NAV_SIZE;
        if ((totalPages - pageNum + 1) < PageConst.MAX_NAV_SIZE) {
            navSize = totalPages % PageConst.PAGE_SIZE;
        }

        int startPage = pageNum % PageConst.MAX_NAV_SIZE == 0 ?
                pageNum - PageConst.MAX_NAV_SIZE + 1 :
                pageNum - (pageNum % PageConst.MAX_NAV_SIZE) + 1;
        int endPage = (startPage + PageConst.MAX_NAV_SIZE - 1) > totalPages ?
                totalPages :
                startPage + PageConst.MAX_NAV_SIZE - 1;

        Page page = new Page(pageNum, totalPages, totalPosts, navSize, startPage, endPage, hasPrev, hasNext);
        model.addAttribute("page", page);

        int startPostNum = (pageNum - 1) * PageConst.PAGE_SIZE;
        List<Post> posts = postService.findByPageNum(startPostNum, PageConst.PAGE_SIZE);
        model.addAttribute("posts", posts);
        // 페이징 처리 끝 ================================================

        return "board";
    }

    private void loginCheck(Member member, Model model) {
        boolean isLogin = false;

        if (member != null) {
            isLogin = true;
        }

        model.addAttribute("isLogin", isLogin);
    }
}
