package features.user;

import features.moneyFlows.application.ChargeMoneyUsecase;
import features.user.presentation.UserSignupInput;
import features.user.application.UserSignupUsecase;
import helpers.BaseTest;
import org.junit.jupiter.api.Test;
import shared.Records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserSignupUsecaseTest extends BaseTest {

    @Test
    void signup() {
        //given
        UserSignupInput input = new UserSignupInput("kanai@test.com");
        //when
        new UserSignupUsecase().run(input);
        //then
        Records records = db.find("select * from users");
        assertEquals(1, records.size());
    }

    @Test
    void invalidなメールは登録できない() {
        //given
        UserSignupInput input = new UserSignupInput("kanaitest.com");
        //when
        try {
            new UserSignupUsecase().run(input);
        } catch (RuntimeException e) {
            assertEquals("invalid email", e.getMessage());
            return;
        }
        fail("unexpected reached");
    }

}

