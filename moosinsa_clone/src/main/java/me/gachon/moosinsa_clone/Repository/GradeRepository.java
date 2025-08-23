package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {
    // create/update 시 FK 세팅에 사용 (getReferenceById 등)
}