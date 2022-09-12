package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.TeacherEntity;

import java.util.List;

public class TeacherRepository extends AbstractRepository {
    public List<TeacherEntity> getTeachers() {
        return getResult(session -> session.createQuery(
                "SELECT entity FROM TeacherEntity",
                TeacherEntity.class
        ).list());
    }

    public void createTeacher(TeacherEntity teacherEntity) {
        modifyEntity(session -> session.persist(teacherEntity));
    }

    public TeacherEntity getTeacherById(Long id) {
        return getResult(session -> session.get(TeacherEntity.class, id));
    }

    public List<TeacherEntity> getTeacherByName(String name) {
        String hql = "FROM TeacherEntity where name = '" + name + "'";
        return getResult(session -> session.createQuery((hql), TeacherEntity.class).list());
    }
}
