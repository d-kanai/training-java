package helpers;

import shared.IMailSender;

public class FakeMailSender implements IMailSender {

    public int callCountSend;
    public String argsEmail;
    public String argsTitle;

    public void send(String email, String title) {
        this.callCountSend++;
        this.argsEmail = email;
        this.argsTitle = title;
    }
}
