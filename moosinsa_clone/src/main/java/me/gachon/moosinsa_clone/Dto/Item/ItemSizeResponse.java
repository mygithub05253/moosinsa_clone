package me.gachon.moosinsa_clone.Dto.Item;

import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Entity.ItemSize;

@Getter
public class ItemSizeResponse {
    private Integer itemSizeId;
    private String itemName;
    private String itemSizeName;
    private String itemSizeDescription;

    public ItemSizeResponse(ItemSize itemSize) {
        this.itemSizeId = itemSize.getItemSizeId();
        this.itemName = itemSize.getItem().getItemName();
        this.itemSizeName = itemSize.getItemSizeName();
        this.itemSizeDescription = itemSize.getItemSizeDescription();
    }
}
