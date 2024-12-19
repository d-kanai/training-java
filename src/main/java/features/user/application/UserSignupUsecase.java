package features.user.application;

import features.user.domain.User;
import features.user.domain.UserRepository;
import features.user.presentation.UserSignupInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSignupUsecase {


    public User run(UserSignupInput input) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(input.getEmail());
        if (!matcher.matches()) {
            throw new RuntimeException("invalid email");
        }
        User user = User.signup(input.getEmail());
        new UserRepository().save(user);
        return user;
    }
}
