package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.TeacherEntity;

import java.util.List;

public class TeacherRepository extends AbstractRepository {

    public void createTeacher(TeacherEntity teacherEntity) {
        modifyEntity(session -> session.persist(teacherEntity));
    }

    public List<TeacherEntity> getTeacherByName(String name) {
        String teacherNameQuery = "FROM TeacherEntity where name = '" + name + "'";
        return getResult(session -> session.createQuery((teacherNameQuery), TeacherEntity.class).list());
    }
}