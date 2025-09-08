package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.ItemSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemSizeRepository extends JpaRepository<ItemSize, Integer> {
    List<ItemSize> findByItem_ItemId(Long itemId);
    void deleteByItem_ItemId(Long itemId);
}
