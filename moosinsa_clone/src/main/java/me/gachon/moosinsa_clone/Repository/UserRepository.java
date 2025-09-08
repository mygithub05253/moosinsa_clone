package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // 비워둬도 OK (CRUD 전부 지원)

    // Spring Data JPA 규칙에 따라 메소드 이름을 작성하면,
    // 자동으로 email 필드를 기준으로 사용자를 찾는 쿼리를 생성해줍니다.
    // Optional<User>는 사용자가 존재할 수도, 존재하지 않을 수도 있다는 것을 의미합니다.
    Optional<User> findByUserId(String userId);
}
