package com.example.project.Controller;

import com.example.project.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){
            model.addAttribute("error", error);
            model.addAttribute("exception", exception);
        return "member/login";
    }
}


