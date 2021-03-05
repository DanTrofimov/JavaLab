package ru.itis.trofimoff.todoapp.utils.mail.sender;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.todoapp.utils.mail.MailLogger;

@Profile("dev")
@Component
public class FakeEmailUtil implements EmailUtil {

    @Override
    public void sendMail(String to, String subject, String from, String text) {
        MailLogger.logMailText();
    }
}
