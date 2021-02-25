package ru.itis.trofimoff.todoapp.utils.mail.generator;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerMailsGenerator implements MailsGenerator {

    @Autowired
    private Configuration configuration;

    @Override
    public String getMailForConfirm(String serverUrl, String code, String contextApp) {
        Template confirmMailTemplate;
        try {
            confirmMailTemplate = configuration.getTemplate("mail/confirm_mail.ftl");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // подготовили данные для шаблона
        Map<String, String> attributes = new HashMap<>();
        attributes.put("server_contextapp", contextApp);
        attributes.put("confirm_code", code);
        attributes.put("server_url", serverUrl);

        StringWriter writer = new StringWriter();
        try {
            // записали в StringWriter текст сообщения
            confirmMailTemplate.process(attributes, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        // получили текст сообщения из шаблона
        return writer.toString();
    }
}
