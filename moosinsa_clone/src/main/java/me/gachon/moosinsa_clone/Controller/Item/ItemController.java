package me.gachon.moosinsa_clone.Controller.Item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemRequest;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemResponse;
import me.gachon.moosinsa_clone.Dto.Item.ItemDetailResponse;
import me.gachon.moosinsa_clone.Dto.Item.ItemListResponse;
import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService; // Item과 관련된 DB 메서드를 저장해둔 서비스 객체 주입
    // 메인 화면 상품 조회
    @GetMapping("/")
    public ResponseEntity<List<ItemListResponse>> getItems() {
        return ResponseEntity.ok()
                .body(itemService.findAll());
    }

    // 상품 상세 조회
    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDetailResponse> getItemDetail(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemService.findById(itemId));
    }

    // 상품 추가 (관리자)
    @PostMapping ("/create")
    public ResponseEntity<CreateItemResponse> createItem(@Valid @RequestBody CreateItemRequest request, UriComponentsBuilder uriBuilder) {
        CreateItemResponse savedItem = itemService.save(request);

        URI location = uriBuilder
                .path("/api/items/{itemId}")
                .buildAndExpand(savedItem.getItemId())
                .toUri();

        return ResponseEntity.created(location).body(savedItem);
    }

    // 상품 검색
    @PostMapping("/search")
    public String searchItems(@RequestParam String keyword, Model model) {
        return "items/search";
    }

    // 상품 카테고리 검색
    @GetMapping("/search/{categoryId}")
    public String getItemsByCategory(@PathVariable Long categoryId, Model model) {
        return "items/category";
    }

    // 상품 삭제 (관리자)
    @DeleteMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        return "redirect:/items";
    }

    // 상품 수정 화면 (관리자)
    @GetMapping("/update/{itemId}")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        return "items/updateForm";
    }

    // 상품 수정 프로세스 (관리자)
    @PutMapping("/update_process/{itemId}")
    public String updateItemProcess(@PathVariable Long itemId) {
        return "redirect:/items/" + itemId;
    }

    // 인기 상품 조회
    @GetMapping("/popular")
    public String getPopularItems(@RequestParam(defaultValue = "30") int limit, Model model) {
        return "items/popular";
    }
}

