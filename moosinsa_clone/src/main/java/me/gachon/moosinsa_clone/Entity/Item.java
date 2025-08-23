package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false)
    private String itemName;
    @Column(nullable = false)
    private String itemBrand;

    @ManyToOne
    @JoinColumn(name = "itemImageId")
    private ItemImage itemImage;

    @Column(nullable = false)
    private Integer itemPrice;
    @Column(nullable = false)
    private Integer itemStock;

    @Builder.Default
    @Column(nullable = false)
    private Integer itemLike = 0;

    @Builder.Default
    @Column(nullable = false)
    private Double itemRating = 0.0;

    @Builder.Default
    @Column(nullable = false)
    private Integer itemReviewAmount = 0;

    @ManyToOne
    @JoinColumn(name = "itemSizeId")
    private ItemSize itemSize;

    @ManyToOne
    @JoinColumn(name = "itemColorId")
    private ItemColor itemColor;

    @Column(columnDefinition = "TEXT")
    private String itemDescription;
    @Column(nullable = false)
    private String itemStatus;
}
