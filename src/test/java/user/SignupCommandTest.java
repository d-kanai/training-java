package user;

import features.user.SignupCommand;
import features.user.SignupInput;
import features.user.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupCommandTest {

    @Test
    void Signup成功() {
        //given
        SignupInput signupInput = new SignupInput("d.kanai");
        //when
        boolean actual = new SignupCommand().run(signupInput);
        //then
        assertEquals(true, actual);
        assertEquals(1, UserRepository.records.size());
        assertEquals("d.kanai", UserRepository.records.get(0).name);
    }
}
