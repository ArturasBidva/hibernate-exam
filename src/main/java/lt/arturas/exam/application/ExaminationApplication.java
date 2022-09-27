package lt.arturas.exam.application;

import lt.arturas.exam.application.Models.*;
import lt.arturas.exam.application.Models.login.StudentLoginRequest;
import lt.arturas.exam.application.Models.login.TeacherLoginRequest;
import lt.arturas.exam.application.Models.login.LoginResponse;
import lt.arturas.exam.application.service.*;
import lt.arturas.exam.application.states.AddQuestionState;
import lt.arturas.exam.application.states.CreateStudentState;
import lt.arturas.exam.application.states.CreateTeacherState;
import lt.arturas.exam.application.states.DeleteState;
import lt.arturas.exam.application.utils.MockDataGenerator;

import java.util.*;

public class ExaminationApplication {
    UserInputReceiver userInputReceiver = new UserInputReceiver();
    LogInService logInService = new LogInService();
    ExamService examService = new ExamService();
    QuestionService questionService = new QuestionService();
    TeacherService teacherService = new TeacherService();
    StudentService studentService = new StudentService();
    StudentResultService studentResultService = new StudentResultService();

    public void start() {
        MockDataGenerator mockDataGenerator = new MockDataGenerator();
        mockDataGenerator.generate();
        menu();
    }

    void menu() {
        String ivestis;
        do {
            menuText();
            ivestis = userInputReceiver.getUserTextInput();
            switch (ivestis) {
                case "1" -> teacherMenu();
                case "2" -> studentMenu();
                case "3" -> createStudentMenu();
                case "4" -> createTeacherMenu();
                case "5" -> System.out.println("Programa baige darba");
                default -> System.out.println("Tokio pasirinkimo nera");
            }
        } while (!ivestis.equals("5"));
    }

    private void createStudentMenu() {
        System.out.println("Iveskite varda:");
        String name = userInputReceiver.getUserTextInput();
        System.out.println("Iveskite pavarde");
        String surname = userInputReceiver.getUserTextInput();
        System.out.println("Iveskite mokinio slapyvardi");
        String username = userInputReceiver.getUserTextInput();
        CreateStudentState createStudentState = studentService.createStudent(name, surname, username);
        switch (createStudentState) {
            case SUCCESS -> {
                System.out.println("Studentas sekmingai sukurtas");
                menu();
            }
            case INCORRECT_SYMBOLS -> {
                System.out.println("Bloga ivestis, skaiciai negalimi, bandykite dar karta");
                System.out.println("noredami iseiti irasykite 'exit',noredami testi spauskite ENTER");
                String input = userInputReceiver.getUserTextInput();
                if (input.equals("exit")) {
                    menu();
                }
                createStudentMenu();
            }
        }
    }

    private void createTeacherMenu() {
        System.out.println("Iveskite varda:");
        String name = userInputReceiver.getUserTextInput();
        CreateTeacherState createTeacherState = teacherService.createTeacher(name);
        switch (createTeacherState) {
            case INCORRECT_SYMBOLS -> {
                System.out.println("Bloga ivestis, skaiciai ir neleistini simboliai negalimi, bandykite dar karta");
                System.out.println("noredami iseiti irasykite 'exit',noredami testi spauskite ENTER");
                String input = userInputReceiver.getUserTextInput();
                if (input.equals("exit")) {
                    menu();
                }
            }
            case SUCCESS -> {
                System.out.println("Mokytojas sekmingai sukurtas");
                menu();
            }
        }
    }

    private void studentMenu() {
        String vardas;
        do {
            System.out.println("Iveskite studento varda, noredami iseiti irasykite 'exit' ");
            vardas = userInputReceiver.getUserTextInput();
            LoginResponse loginResponse = logInService.login(new StudentLoginRequest(vardas));
            if (loginResponse == LoginResponse.SUCCESS) {
                System.out.println("Sveikiname prisijungus");
            } else {
                System.out.println("Tokio studento nera");
                System.out.println("Noredami iseiti is meniu irasykite 'exit'");
            }
            if (loginResponse == LoginResponse.SUCCESS) {
                studentSignedInMenu();
            }
        } while (!vardas.equals("exit"));
    }

    void teacherMenu() {
        String vardas;
        do {
            System.out.println("Iveskite mokytojo varda, noredami iseiti irasykite 'exit'");
            vardas = userInputReceiver.getUserTextInput();
            LoginResponse loginResponse = logInService.login(new TeacherLoginRequest(vardas));
            if (loginResponse == LoginResponse.SUCCESS) {
                System.out.println("Sveikiname prisijungus");
                teacherSignedInMenu();
            } else {
                System.out.println("Tokio mokytojo nera");
                System.out.println("Noredami iseiti is meniu irasykite 'exit'");
            }
        } while (!vardas.equals("exit"));
    }

    void menuText() {
        String menu = """
                1. Prisijungti kaip mokytojui
                2. Prisijungti kaip mokiniui
                3. Mokinio registracija
                4. Mokytojo registracija
                5. Baigti darba.
                """;
        System.out.println(menu);
    }

    void teacherSignedInMenuText() {
        String menu = """
                1. Sukurti egzamina
                2. Sukurti klausima
                3. Prideti klausima i egzamina
                4. Istrinti egzamina
                5. Istrinti klausima is egzamino
                6. Gauti studentu rezultatus
                7. Atsijungti.
                """;
        System.out.println(menu);
    }

    void studentSignedInMenuText() {
        String menu = """
                1. Laikyti egzamina
                2. Perziureti rezultatus.
                3. Koreguoti savo duomenys.
                4. Atsijungti.
                """;
        System.out.println(menu);
    }

    void studentSignedInMenu() {
        String ivestis;
        do {
            studentSignedInMenuText();
            ivestis = userInputReceiver.getUserTextInput();
            switch (ivestis) {
                case "1" -> startExam();
                case "2" -> showExamResults();
                case "3" -> changeUsername();
                case "4" -> menu();
                default -> System.out.println("Tokio pasirinkimo nera");
            }
        } while (!ivestis.equals("4"));
    }

    private void changeUsername() {
        System.out.println("Iveskite nauja username");
        String username = userInputReceiver.getUserTextInput();
        studentService.updateUsername(username, logInService.getCurrentSessionId());
        System.out.printf("Jusu naujas username: %s", username);
    }

    private void showExamResults() {
        System.out.println(studentResultService.getStudentResultsByStudentId(logInService.getCurrentSessionId()));
    }

    private void startExam() {
        examService.getAllExams().forEach(it -> System.out.println(it.asOnlyExamString()));
        System.out.println("Irasykite egzamino id kuri noretumete laikyti");
        Long examId = userInputReceiver.getUserLongInput();
        if (!studentResultService.checkIfHoursPassed(logInService.getCurrentSessionId(), examId)) {
            System.out.println("Laikas dar nepraejo");
        } else {
            userInputReceiver.jumpLineScanner();
            Exam exam = examService.getExamById(examId);
            List<StudentSelection> studentSelections = new ArrayList<>();
            int correctAnswerCount = 0;
            for (Question question : exam.getQuestionList()) {
                System.out.println(question);
                System.out.println("Iveskite teisinga atsakyma");
                String ivestis = userInputReceiver.getUserTextInput();
                StudentSelection studentSelection = new StudentSelection(ivestis, question.getCorrectAnswer());
                studentSelections.add(studentSelection);
            }

            for (StudentSelection studentSelection : studentSelections) {
                if (studentSelection.isCorrect()) {
                    correctAnswerCount++;
                }
            }
            int grade = (correctAnswerCount * 100 / studentSelections.size());

            studentResultService.createStudentResult(logInService.getCurrentSessionId(), exam.getId(), grade);

            System.out.println("Egzaminas baigtas");
        }
    }

    void teacherSignedInMenu() {
        String ivestis;
        do {
            teacherSignedInMenuText();
            ivestis = userInputReceiver.getUserTextInput();
            switch (ivestis) {
                case "1" -> createExam();
                case "2" -> createQuestionTest();
                case "3" -> addQuestionToExam();
                case "4" -> deleteExam();
                case "5" -> deleteQuestionFromExam();
                case "6" -> getAllStudentResults();
                case "7" -> System.out.println("");
                default -> System.out.println("Tokio pasirinkimo nera");
            }
        } while (!ivestis.equals("7"));
    }

    private void deleteQuestionFromExam() {
        examService.getAllExams().forEach(it -> System.out.println(it.asOnlyExamString()));
        System.out.print("Irasykite egzamino id is kurio noretumete istrinti klausima,noredami iseiti irasykite '0'");
        Long examId = userInputReceiver.getUserLongInput();
        if (examId == 0) {
            teacherSignedInMenu();
        }
        if (!examService.checkIfExamExist(examId)) {
            deleteQuestionFromExam();
        }
        if (examService.getExamById(examId) == null) {
            System.out.println("Tokio egzamino nera");
            deleteQuestionFromExam();
        }
        if (examService.getExamById(examId).getQuestionList().isEmpty()) {
            System.out.println("Nera klausimu");
            deleteQuestionFromExam();
        } else {
            System.out.println(examService.getExamById(examId).getQuestionList());
            System.out.println("Irasykite klausimo id kuri noretumete istrinti");
            Long questionId = userInputReceiver.getUserLongInput();
            DeleteState deleteState = examService.deleteQuestionFromExam(questionId, examId);
            switch (deleteState) {
                case SUCCESS -> {
                    System.out.println("Klausimas istrintas sekmingai");
                    userInputReceiver.jumpLineScanner();
                }
                case QUESTION_NOT_FOUND -> {
                    System.out.println("Klausimas nerastas");
                }
            }
        }
    }

    private void deleteExam() {
        System.out.println(examService.getAllExams());
        if (examService.getAllExams().isEmpty()) {
            System.out.println("Egzaminu nera");
            teacherSignedInMenu();
        }
        System.out.println("Irasykite id egzamino kuri noretumete istrinti");
        Long id = userInputReceiver.getUserLongInput();
        examService.deleteExam(id);
        System.out.println("Egzaminas sekmingai istrintas");
    }

    private void addQuestionToExam() {
        System.out.println(examService.getAllExams());
        System.out.println("Irasykite egzamino id kuri noretumete prideti klausima");
        Long examId = userInputReceiver.getUserLongInput();
        System.out.println("=========================");
        System.out.println(questionService.getAllQuestions());
        System.out.println("Irasykite klausimo id kuri noretumete prideti prie egzamino");
        Long questionId = userInputReceiver.getUserLongInput();
        AddQuestionState addQuestionState = examService.addQuestionToExam(questionId, examId);
        switch (addQuestionState) {
            case SUCCESS -> {
                System.out.println("Klausimas sekmingai pridetas");
                teacherSignedInMenu();
            }
            case QUESTION_ALREADY_EXIST -> {
                System.out.println("Toks klausimas jau egzistuoja");
            }
        }
    }

    void createExam() {
        System.out.println("Iveskite egzamino pavadinima:");
        String title = userInputReceiver.getUserTextInput();
        examService.createExam(title, logInService.getCurrentSessionId());
        System.out.println("Egzaminas sekmingai sukurtas");
    }

    void createQuestionTest() {
        System.out.println("Irasykite klausima");
        String questionInput = userInputReceiver.getUserTextInput();
        System.out.println("Irasykite pirma atsakyma");
        String answer1 = userInputReceiver.getUserTextInput();
        System.out.println("Irasykite antra atsakyma");
        String answer2 = userInputReceiver.getUserTextInput();
        System.out.println("Irasykite trecia atsakyma");
        String answer3 = userInputReceiver.getUserTextInput();
        System.out.println("Irasykite teisinga atsakyma");
        String rightAnswer = userInputReceiver.getUserTextInput();
        Question question = new Question(null, questionInput, answer1, answer2, answer3, rightAnswer);
        questionService.createQuestion(question);
        System.out.println("Klausimas sekmingai sukurtas");
    }

    void getAllStudentResults() {
        System.out.println(studentResultService.getAllStudentResults());
    }
}