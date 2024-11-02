package features.user;

import features.user.domain.User;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest extends BaseTest {
    @Test
    void signup() {
        //when
        User user = User.signup("kanai@test.com");
        //then
        assertEquals(User.Plan.NORMAL, user.plan());
        assertEquals("kanai@test.com", user.email());
    }
}
