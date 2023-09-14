package eh7.board.web.signup;

import eh7.board.domain.member.Member;
import eh7.board.domain.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

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
}
