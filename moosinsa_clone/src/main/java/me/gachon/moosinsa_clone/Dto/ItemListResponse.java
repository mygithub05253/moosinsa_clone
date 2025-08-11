package me.gachon.moosinsa_clone.Dto;

import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.Category;
import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Entity.ItemImage;

@Getter
public class ItemListResponse {

    private Long itemId;
    private Category category;
    private String itemName;
    private String itemBrand;
    private ItemImage itemImage;
    private Integer itemPrice;
    private Integer itemLike;
    private Double itemRating;
    private String itemStatus;

    public ItemListResponse(Item item) {
        this.itemId = item.getItemId();
        this.category = item.getCategory();
        this.itemName = item.getItemName();
        this.itemBrand = item.getItemBrand();
        this.itemImage = item.getItemImage();
        this.itemPrice = item.getItemPrice();
        this.itemLike = item.getItemLike();
        this.itemRating = item.getItemRating();
        this.itemStatus = item.getItemStatus();
    }
}
