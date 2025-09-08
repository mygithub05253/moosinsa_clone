package me.gachon.moosinsa_clone.Dto.User;

import lombok.Builder;
import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.User;

@Getter
@Builder
public class UserResponse {
    private final String userId;
    private final String userName;
    private final String phoneNumber;
    private final String address;
    private final String userGrade; // 문자열로 반환되는 등급 이름

    // 모든 필드를 받는 기존 생성자는 그대로 둡니다.
    public UserResponse(String userId, String userName, String phoneNumber, String address, String userGrade) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userGrade = userGrade;
    }

    public UserResponse(User entity) {
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