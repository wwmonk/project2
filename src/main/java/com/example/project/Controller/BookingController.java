package com.example.project.Controller;

import com.example.project.DTO.BookingDTO;
import com.example.project.Entity.StoreEntity;
import com.example.project.Repository.StoreRepository;
import com.example.project.Service.BookingService;
import com.example.project.Util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final StoreRepository storeRepository;
    private final BookingService bookingService;
    @GetMapping("/booking/list")
    public String bookinglist(@RequestParam("username")@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BookingDTO> bookingDTOPage = bookingService.bookingList(pageable);
        Map<String, Integer> pageData = PaginationUtil.Pagination(bookingDTOPage);;
        model.addAllAttributes(pageData);
        model.addAttribute("list", bookingDTOPage);
        return "booking/list";
    }
    @PostMapping("/booking/save")
    public String savePost(BookingDTO bookingDTO) {

        bookingService.bookingSave(bookingDTO);
        return "redirect:/main";
    }
    @GetMapping("/booking/update")
    public String update(Integer id, Model model) {
        BookingDTO bookingDTO = bookingService.bookingRead(id);
        model.addAttribute("data", bookingDTO);

        return "booking/update";
    }
    @PostMapping("/booking/update")
    public String updatePost(BookingDTO bookingDTO) {
        bookingService.bookingUpdate(bookingDTO);

        return "redirect:/booking/list";
    }
    @GetMapping("/booking/save")
    public String save(@RequestParam("storeId") Long storeId, Model model) {
        Optional<StoreEntity> store = storeRepository.findById(storeId);
        if (store.isPresent()) {
            model.addAttribute("storeName", store.get().getName());
            model.addAttribute("storeId", storeId);
        } else {
            // 가게가 존재하지 않는 경우 처리 (예: 에러 페이지로 리디렉션)
            return "redirect:/error";
        }
        return "booking/save";
    }
    @GetMapping("/booking/delete")
    public String delete(Integer id) {
        bookingService.delete(id);
        return "redirect:/booking/list";
    }
}
