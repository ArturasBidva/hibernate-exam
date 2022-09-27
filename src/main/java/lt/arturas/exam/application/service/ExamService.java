package lt.arturas.exam.application.service;

import lt.arturas.exam.application.Models.Exam;
import lt.arturas.exam.application.entity.ExamEntity;
import lt.arturas.exam.application.entity.QuestionEntity;
import lt.arturas.exam.application.entity.TeacherEntity;
import lt.arturas.exam.application.repository.ExamRepository;
import lt.arturas.exam.application.repository.QuestionRepository;
import lt.arturas.exam.application.states.AddQuestionState;
import lt.arturas.exam.application.states.DeleteState;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;


    public ExamService() {
        examRepository = new ExamRepository();
        questionRepository = new QuestionRepository();
    }

    public AddQuestionState addQuestionToExam(Long questionId, Long examId) {
        QuestionEntity questionEntity = questionRepository.getQuestionById(questionId);
        ExamEntity examEntity = examRepository.getExamById(examId);
        if (examEntity.getQuestionEntities().stream().anyMatch(it -> it.getId().equals(questionId))) {
            return AddQuestionState.QUESTION_ALREADY_EXIST;
        }
        examEntity.getQuestionEntities().add(questionEntity);
        examRepository.updateExam(examEntity);
        return AddQuestionState.SUCCESS;
    }

    public DeleteState deleteQuestionFromExam(Long questionId, Long examId) {
        ExamEntity examEntity;
        examEntity = examRepository.getExamById(examId);

        Set<QuestionEntity> questionEntities = examEntity.getQuestionEntities();
        if (questionEntities.stream().noneMatch(i -> i.getId().equals(questionId))) {
            return DeleteState.QUESTION_NOT_FOUND;
        }
        examEntity.getQuestionEntities().stream().filter(questionEntities::contains)
                .collect(Collectors.toSet()).forEach(i -> {
                    if (i.getId().equals(questionId)) {
                        examEntity.getQuestionEntities().remove(i);
                    }
                });
        examRepository.updateExam(examEntity);
        return DeleteState.SUCCESS;

    }

    public void createExam(String title, Long id) {
        long now = System.currentTimeMillis();
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(id);
        ExamEntity exam = new ExamEntity();
        exam.setTitle(title);
        exam.setDate(new Timestamp(now));
        exam.setTeacherEntity(teacherEntity);
        examRepository.createExam(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteExam(examRepository.getExamById(id));
    }

    public List<Exam> getAllExams() {
        return examRepository.getExams().stream().map(Exam::new).collect(Collectors.toList());
    }

    public Exam getExamById(Long id) {
        ExamEntity examEntity;
        try {
            examEntity = examRepository.getExamById(id);
        } catch (NullPointerException e) {
            return null;
        }
        if (examEntity != null) {
            return new Exam(examEntity);
        } else {
            return null;
        }
    }

    public boolean checkIfExamExist(Long id) {
        try {
            examRepository.getExamById(id);
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}