package me.gachon.moosinsa_clone.Dto.User;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String userName;
    private String phoneNumber;
    private String address;
    private String userGrade; // 변경할 Grade의 PK 문자열을 받습니다.
}