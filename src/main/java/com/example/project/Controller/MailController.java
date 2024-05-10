package com.example.project.Controller;

import com.example.project.Service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    @GetMapping("/passwdmail")
    public String EmailForm() {
        return "passwdmail";
    }

    @GetMapping("/send")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text, Model model) {
        mailService.EmailForm(to, subject, text);
        model.addAttribute("message", "성공적으로 메일을 전송하였습니다!");
        return "result";
    }
}
