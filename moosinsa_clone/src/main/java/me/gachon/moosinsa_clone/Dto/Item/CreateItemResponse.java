package me.gachon.moosinsa_clone.Dto.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gachon.moosinsa_clone.Entity.Item;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateItemResponse {
    private Long itemId;

    public CreateItemResponse(Item item) {
        this.itemId = item.getItemId();
    }
}
