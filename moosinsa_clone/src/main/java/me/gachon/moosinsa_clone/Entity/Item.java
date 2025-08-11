package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    private String itemName;
    private String itemBrand;

    @ManyToOne
    @JoinColumn(name = "itemImageId")
    private ItemImage itemImage;

    private Integer itemPrice;
    private Integer itemStock;
    private Integer itemLike;
    private Double itemRating;
    private Integer itemReviewAmount;

    @ManyToOne
    @JoinColumn(name = "itemSizeId")
    private ItemSize itemSize;

    @ManyToOne
    @JoinColumn(name = "itemColorId")
    private ItemColor itemColor;

    private String itemDescription;
    private String itemStatus;
}
