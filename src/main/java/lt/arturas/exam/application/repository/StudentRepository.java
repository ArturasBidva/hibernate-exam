package lt.arturas.exam.application.repository;

import lt.arturas.exam.application.entity.StudentEntity;

import java.util.List;

public class StudentRepository extends AbstractRepository {

    public List<StudentEntity> getStudents() {
        return getResult(session -> session.createQuery(
                "SELECT entity FROM StudentEntity",
                StudentEntity.class
        ).list());
    }

    public void createStudent(StudentEntity studentEntity) {
        modifyEntity(session -> session.persist(studentEntity));
    }

    public List<StudentEntity> getStudentByName(String name) {
        String hql = "FROM StudentEntity where name = '" + name + "'";
        return getResult(session -> session.createQuery((hql), StudentEntity.class).list());
    }

    public StudentEntity getStudentById(Long id) {
        return getResult(session -> session.get(StudentEntity.class, id));
    }

    public void updateStudent(StudentEntity studentEntity) {
        modifyEntity(session -> session.merge(studentEntity));
    }
}
