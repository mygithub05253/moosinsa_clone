package me.gachon.moosinsa_clone.Controller.Category;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Category.CategoryListResponse;
import me.gachon.moosinsa_clone.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryListResponse>> getItems(Model model) {
        System.out.println(model);
        return ResponseEntity.ok()
                .body(categoryService.findAll());
    }
}
