package features.user;

import features.user.domain.User;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserTest extends BaseTest {
    @Test
    void signup() {
        //when
        User user = User.signup("kanai@test.com");
        //then
        assertEquals(User.Plan.NORMAL, user.plan());
        assertEquals("kanai@test.com", user.email());
    }

    @Test
    void upgradeToVip() {
        //given
        User user = User.signup("kanai@test.com");
        //when
        user.upgradeToVip();
        //then
        assertEquals(User.Plan.VIP, user.plan());
    }

    @Test
    void VIPはVIPになれない() {
        //given
        User user = User.signup("kanai@test.com");
        //when
        try {
            user.upgradeToVip();
            user.upgradeToVip();
            fail("unexpected reached");
        } catch (RuntimeException e) {
            assertEquals("already VIP", e.getMessage());
        }
    }
}
