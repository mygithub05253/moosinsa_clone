package me.gachon.moosinsa_clone.Controller.Item;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeBulkRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeResponse;

import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Entity.ItemSize;
import me.gachon.moosinsa_clone.Service.ItemSizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itemSizes")
public class ItemSizeController {
    private final ItemSizeService itemSizeService;

    @GetMapping("/")
    public ResponseEntity<List<ItemSizeResponse>> getItemSizes() {
        return ResponseEntity.ok()
                .body(itemSizeService.findAll());
    }
    @GetMapping("/byItem/{itemId}")
    public ResponseEntity<List<ItemSizeResponse>> getItemSizeByItemId(@PathVariable long itemId) {
        List<ItemSizeResponse> itemSizeResponses = itemSizeService.findByItemId(itemId);
        return ResponseEntity.ok(itemSizeResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<ItemSizeResponse> createItemSize(@Valid @RequestBody ItemSizeRequest request) {
        ItemSizeResponse savedItemSize = itemSizeService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedItemSize);
    }

    /**
     * 벌크 생성/교체: 동일 itemId에 여러 사이즈를 한 번에 추가/교체
     * - 요청: ItemSizeBulkRequest { itemId, replace, sizes: [ {itemSizeName, itemSizeDescription}, ... ] }
     *   * replace=true  → 기존 사이즈 전부 삭제 후 교체
     *   * replace=false → 기존에 추가
     * - 응답: List<ItemSizeResponse>
     */
    @PostMapping("/bulk-create")
    public ResponseEntity<List<ItemSizeResponse>> bulkCreate(@Valid @RequestBody ItemSizeBulkRequest request) {
        List<ItemSizeResponse> savedItemSizes = itemSizeService.bulkSave(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedItemSizes);
    }
}
