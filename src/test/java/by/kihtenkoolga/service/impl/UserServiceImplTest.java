package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.cache.CacheHandler;
import by.kihtenkoolga.dao.UserDAOImpl;
import by.kihtenkoolga.model.User;
import by.kihtenkoolga.util.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static by.kihtenkoolga.util.UserTestBuilder.ALL_DB_USERS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private CacheHandler<Long, User> createUserCashHandler;

    @MockBean
    private UserDAOImpl userDAO;

    @Autowired
    private UserServiceImpl userService;

    @Nested
    public class Create {
        @Test
        void checkCreateShouldReturnObjectWithNewId() {
            User createdUser = UserTestBuilder.user4withNullId().build();

            User expectedUser = UserTestBuilder.USERid4;

            Mockito.doReturn(expectedUser)
                    .when(userDAO)
                    .create(createdUser);

            createdUser = userService.create(createdUser);

            System.out.println(createdUser.getId());

            User cacheUser = createUserCashHandler.get(4L);

            User finalCreatedUser = createdUser;
            assertAll(
                    () -> assertThat(finalCreatedUser).isEqualTo(expectedUser),
                    () -> assertThat(cacheUser).isEqualTo(expectedUser)
            );
        }
    }

    @Nested
    public class FindById {
        @Test
        void checkFindByIdRealUserWithId1() {
            User expectedUser = UserTestBuilder.USERid2;

            Mockito.doReturn(Optional.of(expectedUser))
                    .when(userDAO)
                    .findById(2L);

            User newUser = userService.findById(2L).get();

            User cacheUser = createUserCashHandler.get(2L);

            assertAll(
                    () -> assertThat(newUser).isEqualTo(expectedUser),
                    () -> assertThat(cacheUser).isEqualTo(expectedUser)
            );
        }
    }
    @Nested
    public class FindAll {
        @Test
        void checkFindAllWith3ElementsInDAO() {
            Mockito.doReturn(ALL_DB_USERS)
                    .when(userDAO)
                    .findAll();

            List<User> all = userService.findAll();
            assertThat(all)
                    .hasSize(3);
        }
    }

    @Nested
    public class Update {
        @Test
        void checkUpdateObjectWithId1() {
            User expectedUser = UserTestBuilder.user1withNullId().id(1L).name("Nikolai").build();
            User currentUser = expectedUser;

            Mockito.doNothing()
                    .when(userDAO)
                    .update(currentUser);

            userService.update(currentUser);

            User cacheUser = createUserCashHandler.get(1L);

            assertAll(
                    () -> assertThat(cacheUser).isEqualTo(expectedUser),
                    () -> assertThat(currentUser).isEqualTo(expectedUser)
            );
        }
        @Test
        void checkUpdateNull() {
            Mockito.doNothing()
                    .when(userDAO)
                    .update(null);

            assertThrows(
                    NullPointerException.class,
                    () -> userService.update(null)
            );
        }
    }

    @Nested
    public class Delete {

        @Test
        void checkDeleteById3() {
            Mockito.doNothing().when(userDAO).deleteById(3L);
            userService.deleteById(3L);

            User cacheUser = createUserCashHandler.get(3L);

            assertThat(cacheUser).isNull();
        }

        @Test
        void checkDeleteByIdNull() {
            Mockito.doNothing()
                    .when(userDAO)
                    .deleteById(null);

            assertThrows(
                    NullPointerException.class,
                    () -> userService.deleteById(null)
            );
        }
    }
}
