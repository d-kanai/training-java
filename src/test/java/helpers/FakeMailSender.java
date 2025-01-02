package helpers;

import features.user.domain.Email;
import shared.IMailSender;

public class FakeMailSender implements IMailSender {
    public int callCount;
    public Email argsEmail;

    @Override
    public void send(Email email, String title) {
        callCount++;
        this.argsEmail = email;
    }
}
