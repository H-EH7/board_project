package eh7.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping
    public String home() {
        return "redirect:board";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/sign")
    public String signPage() {
        return "sign";
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
