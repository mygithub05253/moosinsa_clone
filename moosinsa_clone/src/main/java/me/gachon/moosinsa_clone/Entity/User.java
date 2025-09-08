package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "`user`")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id", length = 50)
    private String userId;

    @Column(name = "user_pwd", nullable = false, length = 255)
    private String userPwd;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    // [추가] 닉네임 필드 추가 (nullable = false로 하여 필수 항목으로 지정)
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_grade")
    private Grade userGrade;

    public String getUserGrade() {
        return userGrade != null ? userGrade.getGradeName() : null;
    }
}