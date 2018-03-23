package be.kokw.utility.maillings;

import be.kokw.utility.validation.Warning;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created By Demesmaecker Daniel
 */

/**
 * Allows the user to send an email to a single recipient
 */
public interface SingleRecipient {
    static void sendMail(String recipient, String subject, String text) {
        String from = "d.demesmaecker@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "SoetkinIsEen8erlijkeTrut");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(text, "text/html");
            Transport.send(message);
            Warning.alert("Message sent succesfully", "De boodschap werd met succes verzonden.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            Warning.alert("Messaging Error", mex.getMessage() + "\n \nEr ging iets fout tijdens het versturen van de mail!" );

        }
    }
}
