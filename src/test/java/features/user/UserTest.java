package features.user;

import features.user.domain.User;
import features.user.domain.UserFactory;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest extends BaseTest {
    @Test
    void signup() {
        //when
        User user = new UserFactory().signup("kanai@test.com");
        //then
        assertEquals(User.Plan.NORMAL, user.plan());
        assertEquals("kanai@test.com", user.email().toString());
    }
    @Test
    void upgradeToVip() {
        //given
        User user = new UserFactory().signup("kanai@test.com");
        //when
        user.upgradeToVip();
        //then
        assertEquals(User.Plan.VIP, user.plan());
    }
}
