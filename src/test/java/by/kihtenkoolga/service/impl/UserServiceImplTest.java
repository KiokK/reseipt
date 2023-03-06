package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.UserDAOImpl;
import by.kihtenkoolga.model.User;
import by.kihtenkoolga.util.UserTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

//    @MockBean
//    private CacheHandler<Long, User> createUserCashHandler;

    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void checkCreateShouldReturnObjectWithNewId() {
        User newUser = UserTestBuilder.user4withNullId().build();

        User expectedUser = UserTestBuilder.USERid4;


        Mockito.doReturn(expectedUser)
                .when(userDAO)
                .create(newUser);

//        Mockito.doReturn(expectedUser).when(createUserCashHandler).put(4L, expectedUser);

        newUser = userService.create(newUser);

        Assertions.assertThat(newUser)
                .isEqualTo(expectedUser);
    }

}
