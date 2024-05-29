package com.example.project.Controller;

import com.example.project.DTO.BookingDTO;
import com.example.project.DTO.MemberDTO;
import com.example.project.Rolltype.Role;
import com.example.project.Service.MemberService;
import com.example.project.Util.PaginationUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/save")
    public String save(Model model) {
        return "member/membersave";
    }

    @PostMapping("/member/save")
    public String savepost(MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        log.info("저장");
        memberDTO.setRole(Role.User);
        log.info(memberDTO.toString());
        if(memberService.memberSave(memberDTO) == null){
            redirectAttributes.addFlashAttribute("message", "아이디가 중복되어 실패했습니다. 다시 시도해주세요.");
        }else {
            redirectAttributes.addFlashAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
        }
        return "redirect:/login";
    }
    @GetMapping("/member/delete")
    public String delete(Integer id) {
        memberService.delete(id);
        return "redirect:/main";
    }
    @GetMapping("/member/update")
    public String update(Integer id, Model model) {
        MemberDTO memberDTO=memberService.memberRead(id);
        model.addAttribute("data", memberDTO);
        return "member/memberupdate";
    }
    @PostMapping("/member/update")
    public String updatepost(MemberDTO memberDTO) {
        memberService.memberUpdate(memberDTO);
        return "redirect:/index";
    }
    @GetMapping("/main")
    public String mainpage(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<MemberDTO> memberDTOPage = memberService.memberList(pageable);
        Map<String, Integer> pageData = PaginationUtil.Pagination(memberDTOPage);;
        model.addAllAttributes(pageData);
        model.addAttribute("list", memberDTOPage);
        return "main";
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }
}
