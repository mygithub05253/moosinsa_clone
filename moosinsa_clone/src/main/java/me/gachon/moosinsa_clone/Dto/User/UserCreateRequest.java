package me.gachon.moosinsa_clone.Dto.User;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String userPwd;
    @NotBlank(message = "이름은 필수입니다.")
    private String userName;
    private String phoneNumber;
    private String address;
    private String userGrade; // Grade의 PK 문자열(예: "BRONZE")을 받습니다.
}