package user;

import features.user.application.UserListUsecase;
import features.user.domain.User;
import features.user.domain.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserListUsecaseTest {

    @Test
    void 全てのユーザを取得する() {
        //given
        UserRepository.records = Arrays.asList(new User("tanaka"), new User("suzuki"));
        //when
        List<User> actual = new UserListUsecase().run();
        //then
        assertEquals(2, actual.size());
    }
}
