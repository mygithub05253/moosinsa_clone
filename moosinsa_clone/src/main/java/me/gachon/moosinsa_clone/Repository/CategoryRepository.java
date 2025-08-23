package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
