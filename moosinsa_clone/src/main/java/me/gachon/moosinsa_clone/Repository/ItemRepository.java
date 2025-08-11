package me.gachon.moosinsa_clone.Repository;

import me.gachon.moosinsa_clone.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


/* JpaRepository를 가져올 때 JpaRepository<테이블명, Id칼럼 타입>*/
public interface ItemRepository extends JpaRepository<Item, Long> {
    /**
     * 인터페이스로 만들어도 같은 이름의 클래스 생성
     * 클래스 내부에 DB입출력 함수가 저장되어있음
     */
}
