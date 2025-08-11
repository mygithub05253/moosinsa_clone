package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Category parent; // 계층형 구조

    private String name;
    private Integer depth;
}
