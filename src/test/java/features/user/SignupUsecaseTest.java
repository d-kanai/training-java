package features.user;

import features.user.application.SignupUsecase;
import features.user.domain.User;
import features.user.presentation.SignupInput;
import features.user.domain.UserRepository;
import helpers.TestBase;
import org.junit.jupiter.api.Test;
import shared.Records;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupUsecaseTest extends TestBase {

    @Test
    void Signup成功() {
        //given
        SignupInput signupInput = new SignupInput("d.kanai", "kanai@test.com");
        //when
        User actual = new SignupUsecase().run(signupInput);
        //then
        assertEquals("d.kanai", actual.name());
        Records records = db.find("select * from users");
        assertEquals(1, records.size());
        assertEquals("d.kanai", records.first().get("email"));
    }
}
