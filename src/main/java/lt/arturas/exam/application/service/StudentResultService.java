package lt.arturas.exam.application.service;

import lt.arturas.exam.application.Models.StudentResult;
import lt.arturas.exam.application.Models.presentation.StudentResultPresentation;
import lt.arturas.exam.application.entity.ExamEntity;
import lt.arturas.exam.application.entity.StudentEntity;
import lt.arturas.exam.application.entity.StudentResultEntity;
import lt.arturas.exam.application.repository.ExamRepository;
import lt.arturas.exam.application.repository.StudentRepository;
import lt.arturas.exam.application.repository.StudentResultRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class StudentResultService {
    StudentResultRepository studentResultRepository;
    ExamRepository examRepository;
    StudentRepository studentRepository;

    public StudentResultService() {
        this.studentResultRepository = new StudentResultRepository();
        this.examRepository = new ExamRepository();
        this.studentRepository = new StudentRepository();
    }

    public void createStudentResult(Long studentId, Long examId, int grade) {
        long now = System.currentTimeMillis();
        StudentEntity studentEntity = new StudentEntity();
        ExamEntity examEntity = new ExamEntity();
        studentEntity.setId(studentId);
        examEntity.setId(examId);

        StudentResultEntity studentResultEntity = new StudentResultEntity(
                null,
                new Timestamp(now),
                studentEntity,
                examEntity,
                grade
        );
        studentResultRepository.createStudentResult(studentResultEntity);
    }

    public List<StudentResult> getAllStudentResults() {
        return studentResultRepository
                .getStudentResults()
                .stream()
                .map(StudentResult::new)
                .collect(Collectors.toList());
    }

    public List<StudentResultPresentation> getStudentResultsByStudentId(Long id) {

        return studentResultRepository.getStudentResultByStudentId(id)
                .stream()
                .map(StudentResultPresentation::new)
                .collect(Collectors.toList());
    }
}
