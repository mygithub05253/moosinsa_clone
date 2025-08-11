package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // 비워둬도 OK (CRUD 전부 지원)
}
