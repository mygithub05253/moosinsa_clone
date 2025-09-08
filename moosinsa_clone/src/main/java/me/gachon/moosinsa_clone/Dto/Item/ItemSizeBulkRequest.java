// src/main/java/me/gachon/moosinsa_clone/Dto/Item/ItemSizeBulkRequest.java
package me.gachon.moosinsa_clone.Dto.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemSizeBulkRequest {
    private Long itemId;                    // 대상 Item FK (필수)
    private List<ItemSizeRequest> sizes;    // 이름/설명만 채워도 OK
    private Boolean replace;                // true: 기존 전체 삭제 후 교체, false/null: 추가
}
