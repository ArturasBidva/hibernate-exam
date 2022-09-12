package lt.arturas.exam.application.Models;

import lt.arturas.exam.application.Models.user.Teacher;
import lt.arturas.exam.application.entity.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exam {
    private Long id;
    private String title;
    private Teacher teacher;
    private List<Question> questionList;

    public Exam(ExamEntity examEntity) {
        this.id = examEntity.getId();
        this.title = examEntity.getTitle();
        this.questionList = examEntity.getQuestionEntities().stream().map(Question::new).collect(Collectors.toList());
        this.teacher = new Teacher(examEntity.getTeacherEntity());
    }

    public String asOnlyExamString() {
        return "Exam id: " + id + " | " + "Exam title: " + title + " | " + "Teacher name: " + teacher.getName();
    }
}