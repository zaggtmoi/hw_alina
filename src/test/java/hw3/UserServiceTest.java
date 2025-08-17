package hw3;

import org.example.hw2.exception.DaoException;
import org.example.hw2.model.User;
import org.example.hw2.dao.UserDAO;
import org.example.hw2.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Mockito.doAnswer;

public class UserServiceTest {

    static UserService service;
    static UserDAO userDaoMock;

    @BeforeAll
    static void setService() {
        userDaoMock = Mockito.mock(UserDAO.class);
        service = new UserService(userDaoMock);
    }

    @Test
    public void createNewUserTimeTest() {
        User user = new User("Ivan", "a@i.ru", 53);
        AtomicReference<Instant> time = new AtomicReference<>();

        try {
            doAnswer(invocation -> {
                User argument = invocation.getArgument(0);
                time.set(argument.getCreatedAt());
                return null;
            }).when(userDaoMock).save(ArgumentMatchers.notNull(User.class));

            service.createNewUser(user);
        } catch (DaoException e) {
            throw new RuntimeException("Unexpected dao failure");
        }

        Assertions.assertNotNull(time);
    }

}
