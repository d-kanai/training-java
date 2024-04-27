package user;

import features.user.application.SignupUsecase;
import features.user.presentation.SignupInput;
import features.user.domain.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupUsecaseTest {

    @Test
    void Signup成功() {
        //given
        SignupInput signupInput = new SignupInput("d.kanai");
        //when
        boolean actual = new SignupUsecase().run(signupInput);
        //then
        assertEquals(true, actual);
        assertEquals(1, UserRepository.records.size());
        assertEquals("d.kanai", UserRepository.records.get(0).name);
    }
}
