package helpers;

import shared.IMailSender;

public class FakeMailSender implements IMailSender {
    public int callCount;
    public String argsEmail;

    @Override
    public void send(String email, String title) {
        callCount++;
        this.argsEmail = email;
    }
}
