package shared;

import features.user.domain.Email;

public class MailSender implements IMailSender {
    public void send(Email email, String title) {
        System.out.println("Send Email: " + title);
    }
}