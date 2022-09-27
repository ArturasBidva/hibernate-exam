package lt.arturas.exam.application.Models;

import lt.arturas.exam.application.entity.StudentResultEntity;

public class StudentResult {
    private final String examName;
    private final int grade;
    private final java.sql.Timestamp date;

    private final String teacherName;

    private final String studentName;
    public StudentResult(StudentResultEntity studentResultEntity) {
        this.examName = studentResultEntity.getExamEntity().getTitle();
        this.grade = studentResultEntity.getGrade();
        this.date = studentResultEntity.getDate();
        this.teacherName = studentResultEntity.getExamEntity().getTeacherEntity().getName();
        this.studentName = studentResultEntity.getStudentEntity().getName();
    }

    @Override
    public String toString() {
        return "StudentResultPresentation{" +
                "examName='" + examName + '\'' +
                ", grade=" + grade +
                ", date=" + date +
                ", teacherName='" + teacherName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}