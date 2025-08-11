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

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "address", length = 255)
    private String address;

    // 문자열 PK를 가진 Grade와 FK로 연결
    @ManyToOne
    @JoinColumn(name = "user_grade")
    private Grade userGrade;

    // Grade 이름 반환용 getter
    public String getUserGrade() {
        return userGrade != null ? userGrade.getGradeName() : null;
    }
}
