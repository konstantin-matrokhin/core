package org.kvlt.core.email;

import org.kvlt.core.config.Config;
import org.kvlt.core.utils.Log;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.stream.Collectors;

public class Email {

    private static Properties props;
    private static Session session;

    private static String emailChangeEmail;
    private static String emailChangeConfirmEmail;
    private static String emailConfirmEmail;
    private static String passwordChangeEmail;
    private static String passwordRecoveryEmail;

    private String email;

    public Email(String email) {
        this.email = email;
    }

    static {
        props = new Properties();

        props.put("mail.smtp.host", Config.getSMTP("host"));
        props.put("mail.smtp.port", Config.getSMTP("port"));
        props.put("mail.smtp.socketFactory.port", Config.getSMTP("port"));
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.put("username", Config.getSMTP("email"));
        props.put("password", Config.getSMTP("password"));

        session = createSession();

        loadTemplates();
    }

    public void sendChangeConfirmEmail(String name, String code) {
        String formattedEmail = emailChangeConfirmEmail
                .replace("%name%", name)
                .replace("%code%", code);

        send("Новый ящик // LastCraft", formattedEmail);
    }

    public void sendChangeEmail(String name, String newEmail, String code) {
        String formattedEmail = emailChangeEmail
                .replace("%name%", name)
                .replace("%email%", newEmail)
                .replace("%code%", code);

        send("Смена Email // LastCraft", formattedEmail);
    }

    public void sendPasswordChange(String name) {
        String formattedEmail = passwordChangeEmail
                .replace("%name%", name);
        send("Пароль успешно изменен // LastCraft", formattedEmail);
    }

    public void sendPasswordRecovery(String name, String password) {
        String formattedEmail = passwordRecoveryEmail
                .replace("%name%", name)
                .replace("%password%", password);
        send("Восстановление пароля // LastCraft", formattedEmail);
    }

    public void sendEmailConfirmation(String name, String code) {
        String formattedEmail = emailConfirmEmail
                .replace("%name%", name)
                .replace("%code%", code);
        send("Подтверждение почты // LastCraft", formattedEmail);
    }

    private static String loadHTMLTemplate(String template) throws IOException {
        String dir = "email/";
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(dir + template + ".html");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    public static void loadTemplates() {
        Log.$("Загрузка шаблонов писем..");
        try {
            emailChangeEmail = loadHTMLTemplate("email_change");
            emailChangeConfirmEmail = loadHTMLTemplate("email_change_confirmation");
            emailConfirmEmail = loadHTMLTemplate("email_confirmation");
            passwordChangeEmail = loadHTMLTemplate("password_change");
            passwordRecoveryEmail = loadHTMLTemplate("password_recovery");
            Log.$("Шаблоны загружены.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.err("Не удалось загрузить письма для отправки игрокам!");
        }
    }

    public boolean send(String subject, String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("username")));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Session createSession() {
        return Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        });
    }

    public static Session getSession() {
        return session;
    }

}
