package eh7.board.controller;

import eh7.board.domain.member.Member;
import eh7.board.domain.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final MemberService memberService;

    @GetMapping
    public String home() {
        return "redirect:board";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/sign")
    public String signPage(Member member) {
        return "sign";
    }

    @PostMapping("/sign")
    public String signUp(@Valid Member member, BindingResult bindingResult) {

        boolean isDuplicated = memberService.duplicationCheck(member.getUserId());

        if (isDuplicated) {
            bindingResult.reject("Duplication.userId", "아이디 중복");
        }

        if (bindingResult.hasErrors()) {
            return "sign";
        }

        memberService.signUp(member);
        return "redirect:login";
    }

    @GetMapping("/board")
    public String boardPage() {
        return "board";
    }

    @GetMapping("/write")
    public String writePage() {
        return "write";
    }
}
