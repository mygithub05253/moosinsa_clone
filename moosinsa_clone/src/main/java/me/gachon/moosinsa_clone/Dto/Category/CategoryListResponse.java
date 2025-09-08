package me.gachon.moosinsa_clone.Dto.Category;

import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.Category;
import me.gachon.moosinsa_clone.Entity.Item;
import me.gachon.moosinsa_clone.Entity.ItemImage;

@Getter
public class CategoryListResponse {
    private Integer categoryId;
    private String name;
    private Integer depth;
    private Category parent;


    public CategoryListResponse(Category category) {
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.depth = category.getDepth();
        // 카테고리 추가 시 parent를 기존 Category에서 선택가능하게 변경
        // depth 값도 해당 카테고리 +1로 구현
        this.parent = null;
    }
}
