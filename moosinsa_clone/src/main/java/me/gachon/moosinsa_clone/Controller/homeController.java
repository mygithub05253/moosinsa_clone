package me.gachon.moosinsa_clone.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/items/";
    }
}
