package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String correctAnswer;


    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer_1='" + answer_1 + '\'' +
                ", answer_2='" + answer_2 + '\'' +
                ", answer_3='" + answer_3 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
