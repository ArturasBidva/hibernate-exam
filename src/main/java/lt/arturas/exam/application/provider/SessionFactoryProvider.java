package lt.arturas.exam.application.provider;

import lt.arturas.exam.application.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionFactoryProvider {

    private static SessionFactoryProvider instance;
    private final SessionFactory sessionFactory;

    private SessionFactoryProvider() {
        Configuration configuration = new Configuration();
        configuration.setProperties(createHibernateProperties());

        //Mapping all entities
        configuration.addAnnotatedClass(StudentEntity.class);
        configuration.addAnnotatedClass(TeacherEntity.class);
        configuration.addAnnotatedClass(QuestionEntity.class);
        configuration.addAnnotatedClass(StudentResultEntity.class);
        configuration.addAnnotatedClass(ExamEntity.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryProvider getInstance() {
        if (instance == null) {
            instance = new SessionFactoryProvider();
        }

        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Properties createHibernateProperties() {
        Properties p = new Properties();
        p.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        p.put(Environment.DRIVER, "org.postgresql.Driver");
        p.put(Environment.URL, "jdbc:postgresql://localhost/testukas");
        p.put(Environment.USER, "postgres");
        p.put(Environment.PASS, "bomzas123A@");
        p.put(Environment.SHOW_SQL, "true");
        //p.put(Environment.HBM2DDL_AUTO,"update");
        p.put(Environment.HBM2DDL_AUTO, "create-drop");
        p.put(Environment.LOG_JDBC_WARNINGS, "true");
        return p;
    }
}