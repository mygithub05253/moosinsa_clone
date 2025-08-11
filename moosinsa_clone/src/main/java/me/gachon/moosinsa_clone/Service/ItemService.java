package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.ItemListResponse;
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
                .map(ItemListResponse::new)
                .toList();

        return ItemListResponses;
    }
}
