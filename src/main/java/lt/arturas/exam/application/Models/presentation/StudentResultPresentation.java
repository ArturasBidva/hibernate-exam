package lt.arturas.exam.application.Models.presentation;

import lt.arturas.exam.application.entity.StudentResultEntity;

public class StudentResultPresentation {
    private String examName;
    private int grade;
    private java.sql.Timestamp date;

    private String teacherName;

    public StudentResultPresentation(StudentResultEntity studentResultEntity) {
        this.examName = studentResultEntity.getExamEntity().getTitle();
        this.grade = studentResultEntity.getGrade();
        this.date = studentResultEntity.getDate();
        this.teacherName = studentResultEntity.getExamEntity().getTeacherEntity().getName();
    }

    @Override
    public String toString() {
        return "StudentResultPresentation{" +
                "examName='" + examName + '\'' +
                ", grade=" + grade +
                ", date=" + date +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}