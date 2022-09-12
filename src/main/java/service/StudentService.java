package service;

import Models.user.Student;
import entity.StudentEntity;
import repository.StudentRepository;
import states.CreateStudentState;
import utils.Validator;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;
    private final Validator validator;

    public StudentService() {
        studentRepository = new StudentRepository();
        validator = new Validator();
    }

    public Student getStudentByName(String name) {
        List<StudentEntity> studentByName = studentRepository.getStudentByName(name);
        if (studentByName.size() > 0) {
            return new Student(studentByName.get(0));
        }
        return null;
    }

    public Student getStudentById(Long id) {
        return new Student(studentRepository.getStudentById(id));
    }

    public CreateStudentState createStudent(String name, String surname, String username) {
        if (validator.hasDigits(name) || validator.hasDigits(surname)) {
            return CreateStudentState.INCORRECT_SYMBOLS;
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(name);
        studentEntity.setSurname(surname);
        studentEntity.setUsername(username);
        studentRepository.createStudent(studentEntity);
        return CreateStudentState.SUCCESS;
    }

    public void updateUsername(String username, Long sessionId) {
        StudentEntity student = studentRepository.getStudentById(sessionId);
        student.setUsername(username);
        studentRepository.updateStudent(student);
    }
}