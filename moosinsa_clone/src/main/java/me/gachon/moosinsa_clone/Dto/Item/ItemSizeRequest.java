package me.gachon.moosinsa_clone.Dto.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gachon.moosinsa_clone.Entity.ItemSize;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSizeRequest {
    private String itemSizeName;
    private String itemSizeDescription;
    private Long itemId;

    public ItemSizeRequest(ItemSize itemSize) {
        this.itemSizeName = itemSize.getItemSizeName();
        this.itemSizeDescription = itemSize.getItemSizeDescription();
        this.itemId = itemSize.getItem().getItemId();
    }
}
