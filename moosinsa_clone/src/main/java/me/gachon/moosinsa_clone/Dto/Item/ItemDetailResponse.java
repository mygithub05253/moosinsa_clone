// src/main/java/me/gachon/moosinsa_clone/Dto/Item/ItemDetailResponse.java
package me.gachon.moosinsa_clone.Dto.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gachon.moosinsa_clone.Entity.Item;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDetailResponse {

    private Long itemId;
    private String itemName;
    private String itemBrand;
    private String itemDescription;
    private Integer itemPrice;
    private String itemStatus;
    private Integer itemStock;
    private Long categoryId;  // 필요 시 사용

    public ItemDetailResponse(Item item) {
        this.itemId = item.getItemId();
        this.itemName = item.getItemName();
        this.itemBrand = item.getItemBrand();
        this.itemDescription = item.getItemDescription();
        this.itemPrice = item.getItemPrice();
        this.itemStatus = item.getItemStatus();
        this.itemStock = item.getItemStock();
    }
}