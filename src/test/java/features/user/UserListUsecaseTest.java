package features.user;

import features.user.application.UserListUsecase;
import features.user.domain.User;
import helpers.TestDataFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserListUsecaseTest {

    @Test
    void 全てのユーザを取得する() {
        //given
        TestDataFactory.createUser();
        TestDataFactory.createUser();
        //when
        List<User> actual = new UserListUsecase().run();
        //then
        assertEquals(2, actual.size());
    }
}
