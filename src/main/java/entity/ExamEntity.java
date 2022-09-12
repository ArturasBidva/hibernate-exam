package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exam")
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    public java.sql.Timestamp date;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    private TeacherEntity teacherEntity;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(
            name = "question_exam_join",
            joinColumns = {
                    @JoinColumn(name = "exam_id", referencedColumnName = "id", table = "ExamEntity")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "question_id", referencedColumnName = "id", table = "QuestionEntity")
            }
    )
    Set<QuestionEntity> questionEntities;

}
