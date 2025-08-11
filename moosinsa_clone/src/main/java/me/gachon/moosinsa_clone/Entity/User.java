package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userId;

    private String userPwd;
    private String userName;
    private String phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "userGrade")
    private Grade grade;
}
