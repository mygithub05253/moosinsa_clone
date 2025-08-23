package me.gachon.moosinsa_clone.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class homeController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
