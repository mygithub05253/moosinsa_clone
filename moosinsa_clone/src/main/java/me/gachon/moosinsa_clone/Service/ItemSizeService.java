package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeBulkRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemSizeResponse;
import me.gachon.moosinsa_clone.Entity.*;
import me.gachon.moosinsa_clone.Repository.ItemRepository;
import me.gachon.moosinsa_clone.Repository.ItemSizeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemSizeService {

    private final ItemSizeRepository itemSizeRepository;
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<ItemSizeResponse> findAll() {
        List<ItemSizeResponse> itemSizeResponses = itemSizeRepository.findAll()
                .stream()
                .map(ItemSizeResponse::new)
                .toList();
        return itemSizeResponses;
    }

    @Transactional(readOnly = true)
    public List<ItemSizeResponse> findByItemId(Long itemId) {
        // 선택: 아이템 존재 확인 (원하지 않으면 주석 처리 가능)
        if (!itemRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Item not found: " + itemId);
        }

        List<ItemSize> list = itemSizeRepository.findByItem_ItemId(itemId);
        return list.stream()
                .map(ItemSizeResponse::new)   // 엔티티→DTO 변환 (생성자 사용)
                .toList();
    }

    public ItemSizeResponse save( ItemSizeRequest request) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found" + request.getItemId()));

        if (request.getItemSizeName() == null || request.getItemSizeName().isBlank()) {
            throw new IllegalArgumentException("itemSizeName is required");
        }

        ItemSize itemSize = ItemSize.builder()
                .item(item)
                .itemSizeName(request.getItemSizeName())
                .itemSizeDescription(request.getItemSizeDescription())
                .build();
        ItemSize saved = itemSizeRepository.save(itemSize);
        return new ItemSizeResponse(saved);
    }

    /**
     * 벌크 생성/교체: /api/itemSizes/bulk-create
     * - replace=true면 기존 사이즈 전량 삭제 후 저장
     * - replace=false/null이면 기존에 추가
     */
    public List<ItemSizeResponse> bulkSave(ItemSizeBulkRequest request) {
        if (request.getItemId() == null) throw new IllegalArgumentException("itemId is required");

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found" + request.getItemId()));

        boolean replace = Boolean.TRUE.equals(request.getReplace());

        if (replace) {
            // 파생쿼리 메서드 필요 : ItemSizeRepository에 선언
            // void deleteByItem_ItemId(Long itemId);
            itemSizeRepository.deleteByItem_ItemId(item.getItemId());
        }

        List<ItemSizeRequest> sizes = request.getSizes();
        if (sizes == null || sizes.isEmpty()) return List.of();

        List<ItemSize> toSave = new ArrayList<>();
        for (ItemSizeRequest size : sizes) {
            if(size == null) continue;
            String name = size.getItemSizeName() == null ? "" : size.getItemSizeName().trim();
            if (name.isEmpty()) continue;

            ItemSize itemSize = ItemSize.builder()
                    .item(item)
                    .itemSizeName(name)
                    .itemSizeDescription(size.getItemSizeDescription())
                    .build();
            toSave.add(itemSize);
        }

        if (toSave.isEmpty()) return List.of();

        List<ItemSize> saved = itemSizeRepository.saveAll(toSave);
        List<ItemSizeResponse> result = new ArrayList<>(saved.size());
        for (ItemSize itemSize : saved) {
            result.add(new ItemSizeResponse(itemSize));
        }
        return result;
    }
}
