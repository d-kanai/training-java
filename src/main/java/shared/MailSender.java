package shared;

public class MailSender implements IMailSender {
    public void send(String email, String title) {
        System.out.println("Send Email: " + title);
    }
}
