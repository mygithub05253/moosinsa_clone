package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "grade")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    @Id
    @Column(name = "user_grade", length = 20, nullable = false)
    private String userGrade;

    @Column(name = "grade_name", nullable = false, length = 50)
    private String gradeName;

    @Column(name = "grade_discount", nullable = false)
    private Float gradeDiscount;

    @Column(name = "grade_benefit", length = 255)
    private String gradeBenefit;
}
