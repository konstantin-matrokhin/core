package org.kvlt.core.email;

import org.kvlt.core.config.Config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    private static Properties props;
    private static Session session;
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
    }

    public boolean send(EmailType emailType) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(props.getProperty("username")));
            message.setSubject(emailType.getSubject());
            message.setText(emailType.getContent());
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
