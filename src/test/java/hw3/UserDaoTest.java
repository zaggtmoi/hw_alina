package hw3;


import org.example.hw2.dao.UserDAO;
import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class UserDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.6")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    static SessionFactory sessionFactory;
    static UserDAO userDAO;

    @BeforeAll
    public static void setUp() {
        postgres.start();

        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("connection.url", postgres.getJdbcUrl());
        configuration.setProperty("connection.username", postgres.getUsername());
        configuration.setProperty("connection.password", postgres.getPassword());
        configuration.setProperty("format_sql", "true");

        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
        userDAO = new UserDAO(sessionFactory);
    }

    @AfterAll
    public static void tearDown() {
        postgres.stop();
    }

    @BeforeEach
    public void cleanTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM org.example.hw2.model.User";
            session.createQuery(hql);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't run tests: error while table User erase. ", e);
        }
    }


    @Test
    public void getByIdNoUserTest() {
        User noUser = userDAO.findById(1);
        Assertions.assertNull(noUser);
    }

    @Test
    public void deleteNoUser() {
        Assertions.assertThrows(
                DaoException.class, () ->
                userDAO.delete(null)
        );
    }

}
