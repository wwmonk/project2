package com.example.project.Controller;

import com.example.project.DTO.StoreDTO;
import com.example.project.Service.StoreService;
import com.example.project.Util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @GetMapping("/chfood")
    public String chfood(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<StoreDTO> storeDTOPage = storeService.storeList(pageable);
        Map<String, Integer> pageData = PaginationUtil.Pagination(storeDTOPage);
        model.addAllAttributes(pageData);
        model.addAttribute("list", storeDTOPage);
        return "store/chfood";
    }
    @GetMapping("/japan")
    public String japan(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<StoreDTO> storeDTOPage = storeService.storeList(pageable);
        Map<String, Integer> pageData = PaginationUtil.Pagination(storeDTOPage);
        model.addAllAttributes(pageData);
        model.addAttribute("list", storeDTOPage);
        return "store/japan";
    }
    @GetMapping("/food")
    public String food(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<StoreDTO> storeDTOPage = storeService.storeList(pageable);
        Map<String, Integer> pageData = PaginationUtil.Pagination(storeDTOPage);
        model.addAllAttributes(pageData);
        model.addAttribute("list", storeDTOPage);
        return "store/food";
    }

    @GetMapping("/address")
    public String address(Long id, Model model) {
        StoreDTO storeDTO = storeService.read(id);
        model.addAttribute("data", storeDTO);

        return "address";
    }
        @GetMapping("/test")
    public String test(){
        return "test";
        }
    }

