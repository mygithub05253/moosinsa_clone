package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.Category.CategoryListResponse;
import me.gachon.moosinsa_clone.Dto.Item.ItemListResponse;
import me.gachon.moosinsa_clone.Entity.Category;
import me.gachon.moosinsa_clone.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> findAll() {

        List<CategoryListResponse> CategoryListResponses = categoryRepository.findAll()
                .stream() //테이블의 데이터를 행별로 하나씩 추출하는 명령어
                .map(CategoryListResponse::new) // 추출된 데이터를 응답 객체 하나하나에 매핑
                .toList(); // 생성된 응답객체를 리스트 형태로 묶음

        return CategoryListResponses;
    }
}
