package lt.arturas.exam.application.utils;

import lt.arturas.exam.application.Models.Question;
import lt.arturas.exam.application.service.*;

public class MockDataGenerator {
    StudentService studentService;
    TeacherService teacherService;
    ExamService examService;
    QuestionService questionService;
    StudentResultService studentResultService;

    public MockDataGenerator() {
        studentService = new StudentService();
        teacherService = new TeacherService();
        examService = new ExamService();
        questionService = new QuestionService();
        studentResultService = new StudentResultService();
    }

    public void generate() {
        mockStudents();
        mockTeachers();
        mockExams();
        mockQuestions();
        addMockQuestionsToExams();
        mockStudentResults();
    }

    private void mockStudents() {
        studentService.createStudent("Arturas", "Watafaking", "Bidva");
        studentService.createStudent("Arturass", "Watafakingg", "Bidvaa");
    }

    private void mockTeachers() {
        teacherService.createTeacher("Benas");
        teacherService.createTeacher("Benasss");
    }

    private void mockExams() {
        examService.createExam("Egzaminas", 1L);
        examService.createExam("EgzaminasDu", 2L);
    }

    private void mockQuestions() {
        Question question = new Question(null, "Lietuvos sostine?", "Jonava", "Kaunas", "Vilnius", "Vilnius");
        questionService.createQuestion(question);
        Question question2 = new Question(null, "Lietuvos sostine?", "Jonava", "Kaunas", "Vilnius", "Vilnius");
        questionService.createQuestion(question2);
        Question question3 = new Question(null, "Lietuvos sostine?", "Jonava", "Kaunas", "Vilnius", "Vilnius");
        questionService.createQuestion(question3);
        Question question4 = new Question(null, "Lietuvos sostine?", "Jonava", "Kaunas", "Vilnius", "Vilnius");
        questionService.createQuestion(question4);
    }

    private void addMockQuestionsToExams() {
        examService.addQuestionToExam(1L, 1L);
        examService.addQuestionToExam(2L, 1L);
        examService.addQuestionToExam(2L, 2L);
        examService.addQuestionToExam(3L, 2L);
    }

    private void mockStudentResults() {
        studentResultService.createStudentResult(1L, 2L, 55);
        studentResultService.createStudentResult(2L, 1L, 55);
        studentResultService.createStudentResult(2L, 2L, 66);
    }
}
