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
    private final String userGrade; // 문자열/엔티티 어떤 형태여도 toString()으로 안전하게 표현

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .userGrade(String.valueOf(user.getUserGrade()))
                .build();
    }
}
