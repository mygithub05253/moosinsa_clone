package me.gachon.moosinsa_clone.Dto.User;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequest {
    private String userId;
    private String userPwd;
}