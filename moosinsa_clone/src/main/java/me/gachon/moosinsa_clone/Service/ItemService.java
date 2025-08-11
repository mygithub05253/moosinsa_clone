package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Item.ItemListResponse;
import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemListResponse> findAll() {

        List<ItemListResponse> ItemListResponses = itemRepository.findAll()
                .stream() //테이블의 데이터를 행별로 하나씩 추출하는 명령어
                .map(ItemListResponse::new) // 추출된 데이터를 응답 객체 하나하나에 매핑
                .toList(); // 생성된 응답객체를 리스트 형태로 묶음

        return ItemListResponses;
    }

    public Item findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return item;
    }
}
