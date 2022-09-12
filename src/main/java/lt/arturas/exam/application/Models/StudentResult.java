package lt.arturas.exam.application.Models;

import lt.arturas.exam.application.Models.user.Student;
import lt.arturas.exam.application.entity.StudentResultEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentResult {
    private Long StudentResultId;
    private Student student;
    private Exam exam;
    private int grade;

    public StudentResult(StudentResultEntity studentResultEntity) {
        this.StudentResultId = studentResultEntity.getId();
        this.student = new Student(studentResultEntity.getStudentEntity());
        this.exam = new Exam(studentResultEntity.getExamEntity());
        this.grade = studentResultEntity.getGrade();
    }
}