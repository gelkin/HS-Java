package space.harbour.hw10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import space.harbour.hw10.dao.AssessmentDataSetDAO;
import space.harbour.hw10.dao.UserDataSetDAO;
import space.harbour.hw10.dataset.AssessmentDataSet;
import space.harbour.hw10.dataset.UserDataSet;

import java.util.function.Function;

public class MyDBService implements DBService {
    private SessionFactory sessionFactory;

    public MyDBService() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AssessmentDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/jdbc_playground");
        configuration.setProperty("hibernate.connection.username", "junk_user");
        configuration.setProperty("hibernate.connection.password", "junk_user");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    public String getLocalStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    public void saveUser(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(user);
        }
    }

    public void saveAssessment(AssessmentDataSet assessment) {
        try (Session session = sessionFactory.openSession()) {
            AssessmentDataSetDAO dao = new AssessmentDataSetDAO(session);
            dao.save(assessment);
        }
    }

    public UserDataSet readUser(long id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
    }

    public AssessmentDataSet readAssessment(long id) {
        return runInSession(session -> {
            AssessmentDataSetDAO dao = new AssessmentDataSetDAO(session);
            return dao.read(id);
        });
    }

    public void shutdown() {
        sessionFactory.close();
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
