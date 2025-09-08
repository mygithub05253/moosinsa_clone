package me.gachon.moosinsa_clone.Dto.Item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gachon.moosinsa_clone.Entity.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateItemRequest {

    private Long itemId;
    private Integer categoryId;
    private String itemName;
    private String itemBrand;
    private Integer itemImageId;
    private Integer itemPrice;
    private Integer itemStock;
    private String itemDescription;
    private String itemStatus;
}
