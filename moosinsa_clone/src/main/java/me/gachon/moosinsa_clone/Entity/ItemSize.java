package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemSizeId;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private String itemSizeName;
    private String itemSizeDescription;
}
