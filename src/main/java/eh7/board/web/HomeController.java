package eh7.board.web;

import eh7.board.SessionConst;
import eh7.board.domain.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:board";
    }

    @GetMapping("/board")
    public String boardPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                            Model model) {

        loginCheck(member, model);

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
