package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemRequest;
import me.gachon.moosinsa_clone.Dto.Item.ItemListResponse;
import me.gachon.moosinsa_clone.Entity.*;
import me.gachon.moosinsa_clone.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemImageRepository itemImageRepository;
    private final ItemSizeRepository itemSizeRepository;
    private final ItemColorRepository itemColorRepository;

    public List<ItemListResponse> findAll() {

        List<ItemListResponse> ItemListResponses = itemRepository.findAll()
                .stream() //테이블의 데이터를 행별로 하나씩 추출하는 명령어
                .map(ItemListResponse::new) // 추출된 데이터를 응답 객체 하나하나에 매핑
                .toList(); // 생성된 응답객체를 리스트 형태로 묶음

        return ItemListResponses;
    }

    public Item findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null); // 상품 id로 상세 검색
        return item;
    }

    public Item save(CreateItemRequest request) {
        Category category = null;
        ItemImage itemImage = null;
        ItemSize itemSize = null;
        ItemColor itemColor = null;

        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리"));
        }

        Item item = Item.builder()
                .itemId(request.getItemId())
                .category(category)
                .itemName(request.getItemName())
                .itemBrand(request.getItemBrand())
                .itemImage(itemImage)
                .itemPrice(request.getItemPrice())
                .itemStock(request.getItemStock())
                .itemSize(itemSize)
                .itemColor(itemColor)
                .itemDescription(request.getItemDescription())
                .itemStatus(request.getItemStatus())
                .build();

        return itemRepository.save(item);
    }
}
