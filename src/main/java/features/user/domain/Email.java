package features.user.domain;

import shared.ValueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email extends ValueObject {
    public Email(String value) {
        super(value);

        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
        if (!matcher.matches()) {
            throw new RuntimeException("invalid email");
        }
    }

    public static Email create(String value) {
        return new Email(value);
    }

}
