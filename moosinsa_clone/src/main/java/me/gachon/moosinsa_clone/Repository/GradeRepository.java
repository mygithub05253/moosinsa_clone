package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, String> {
    // create/update 시 FK 세팅에 사용 (getReferenceById 등)

    // 이 메소드 선언을 추가하면 Spring Data JPA가 자동으로 쿼리를 생성하여 구현해줍니다.
    Optional<Grade> findByUserGrade(String userGrade);
}