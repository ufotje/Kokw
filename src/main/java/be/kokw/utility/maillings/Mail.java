package be.kokw.utility.maillings;


import be.kokw.bean.Member;
import be.kokw.utility.validation.Warning;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created By Demesmaecker Daniel
 */

public interface Mail {

    /**
     * Allows to user to send e-mails to a list of recipients
     * @param recipients String
     * @param subject String
     * @param text String
     */
    static void sendMail(List<Member> recipients, String subject, String text) {
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
            for(Member recipient : recipients) {
                message.addRecipients(Message.RecipientType.BCC,
                        InternetAddress.parse(recipient.getEmail()));
            }
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
