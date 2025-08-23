package me.gachon.moosinsa_clone.Dto.User;

import lombok.Builder;
import lombok.Getter;
import me.gachon.moosinsa_clone.Entity.User;

@Getter
@Builder
public class UserListResponse {
    private final String userId;
    private final String userName;
    private final String userGrade;

    public static UserListResponse from(User user) {
        return UserListResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userGrade(user.getUserGrade())
                .build();
    }
}