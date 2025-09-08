package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemRequest;
import me.gachon.moosinsa_clone.Dto.Item.CreateItemResponse;
import me.gachon.moosinsa_clone.Dto.Item.ItemDetailResponse;
import me.gachon.moosinsa_clone.Dto.Item.ItemListResponse;
import me.gachon.moosinsa_clone.Entity.*;
import me.gachon.moosinsa_clone.Repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional(readOnly = true)
    public ItemDetailResponse findById(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found: " + itemId));
        return new ItemDetailResponse(item);
    }


    public CreateItemResponse save(CreateItemRequest request) {
        Category category = null;
        ItemImage itemImage = null;


        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리"));
        }

        Item item = Item.builder()
                .category(category)
                .itemName(request.getItemName())
                .itemBrand(request.getItemBrand())
                .itemImage(itemImage)
                .itemPrice(request.getItemPrice())
                .itemStock(request.getItemStock())
                .itemDescription(request.getItemDescription())
                .itemStatus(request.getItemStatus())
                .build();

        itemRepository.save(item);
        return new CreateItemResponse(item.getItemId());
    }
}
