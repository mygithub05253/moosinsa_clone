package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<ItemSize> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @Builder.Default
    private List<ItemColor> colors = new ArrayList<>();

    // 편의 메서드
    public void addSize(ItemSize s) { sizes.add(s); s.setItem(this); }
    public void clearSizes() {
        for (ItemSize s : sizes) s.setItem(null);
        sizes.clear();
    }
    public void addColor(ItemColor c) { colors.add(c); c.setItem(this); }
    public void clearColors() {
        for (ItemColor c : colors) c.setItem(null);
        colors.clear();
    }


    @Column(columnDefinition = "TEXT")
    private String itemDescription;
    @Column(nullable = false)
    private String itemStatus;
}
