package me.gachon.moosinsa_clone.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {
    @Id
    private Integer userGrade;

    private String gradeName;
    private Float gradeDiscount;
    private String gradeBenefit;
}
