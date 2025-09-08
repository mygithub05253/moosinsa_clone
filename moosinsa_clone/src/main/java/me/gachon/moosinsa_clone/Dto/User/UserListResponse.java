package me.gachon.moosinsa_clone.Dto.User;
import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.User;

@Getter
public class UserListResponse {
    // 프론트엔드에 보여줄 사용자 정보 필드들
    private String userId;
    private String userName;
    private String phoneNumber;
    private String address;
    private String userGrade;

    /**
     * User 엔티티를 UserListResponse DTO로 변환하는 생성자
     * @param entity - 데이터베이스에서 조회한 User 엔티티 객체
     */
    public UserListResponse(User entity) {
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.phoneNumber = entity.getPhoneNumber();
        this.address = entity.getAddress();

        // [수정] entity.getUserGrade()는 이미 등급 이름(String)을 반환하므로,
        // 추가적인 메소드 호출 없이 바로 사용합니다.
        // 만약 등급이 null일 경우 "등급없음"으로 표시합니다.
        String gradeName = entity.getUserGrade();
        this.userGrade = (gradeName != null) ? gradeName : "등급없음";
    }
}