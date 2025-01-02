package shared;

import features.user.domain.Email;

public interface IMailSender {
    void send(Email email, String title);
}
