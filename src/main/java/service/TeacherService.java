package service;

import Models.user.Teacher;
import entity.TeacherEntity;
import repository.ExamRepository;
import repository.TeacherRepository;
import states.CreateTeacherState;
import utils.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ExamRepository examRepository;
    private final Validator validator;

    public TeacherService() {
        teacherRepository = new TeacherRepository();
        examRepository = new ExamRepository();
        validator = new Validator();
    }

    public CreateTeacherState createTeacher(String name) {
        if(validator.hasDigits(name)){
            return CreateTeacherState.INCORRECT_SYMBOLS;
        }
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName(name);
        teacherRepository.createTeacher(teacherEntity);
        return CreateTeacherState.SUCCESS;
    }
    public Teacher getTeacherByName(String name) {
        List<TeacherEntity> teacherByName = teacherRepository.getTeacherByName(name);
        if(teacherByName.size() > 0){
            return new Teacher(teacherByName.get(0));
        }
        return null;
    }

    public boolean checkIfExistTeacher(String name) {
        List<TeacherEntity> teacherByName = teacherRepository.getTeacherByName(name);
        if (teacherByName.size() > 0) {
            return true;
        }
        return false;
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.getTeachers().stream().map(Teacher::new).collect(Collectors.toList());
    }

    public TeacherEntity getTeacherById(Long id) {
        return teacherRepository.getTeacherById(id);
    }
}

