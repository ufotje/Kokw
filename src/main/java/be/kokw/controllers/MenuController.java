package be.kokw.controllers;

import be.kokw.bean.Member;
import be.kokw.controllers.members.search.MembersByNotPayed;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.ChangeScene;
import be.kokw.utility.NewStage;
import be.kokw.utility.Warning;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */

@Controller
public class MenuController {
    public static Stage window;
    private MemberRepo repo;

    @Autowired
    private void setRepo(@Qualifier("memberRepo") MemberRepo repo){
        this.repo = repo;
    }

    //BookMethods
    //Add a Book
    @FXML
    private void addBook() throws Exception {
        ChangeScene.init("/fxml/books/create/addBook.fxml", "addBook");
    }

    //Find a Book
    @FXML
    private void findBookByTitle() throws Exception {
        window = NewStage.getStage("Vind Boek op Titel!", "/fxml/books/search/dialogpaneByTitle.fxml");
        window.show();
    }

    @FXML
    private void findBookByAuthor() throws Exception {
        window = NewStage.getStage("Vul de auteurs naam in!", "/fxml/books/search/byName.fxml");
        window.show();
    }

    @FXML
    private void findBookByPublisher() throws Exception {
        window = NewStage.getStage("Vind Boeken op uitgeverij!", "/fxml/books/search/dialogpaneByPublisher.fxml");
        window.show();
    }

    @FXML
    private void findBookByTopic() throws Exception {
        window = NewStage.getStage("Vind Boeken op onderwerp!", "/fxml/books/search/byTopicDialog.fxml");
        window.show();
    }

    //Update a book
    @FXML
    private void updateBookByTitle() throws Exception {
        window = NewStage.getStage("Update een Boek!", "/fxml/books/update/updateDialog.fxml");
        window.show();
    }

    //Delete a Book
    @FXML
    private void deleteBookByTitle() throws Exception {
        window = NewStage.getStage("Verwijder Boek op Titel!", "/fxml/books/delete/dialogpaneByTitle.fxml");
        window.show();
    }

    //checkOut Methods
    @FXML
    private void checkOut() throws Exception {
        window = NewStage.getStage("Check-Out Book!", "/fxml/checkOut/books/checkOutDialog.fxml");
        window.show();
    }

    @FXML
    private void returnBook() throws Exception {
        window = NewStage.getStage("Return Book!", "/fxml/checkOut/books/returnDialog.fxml");
        window.show();
    }

    @FXML
    private void prolong() throws Exception {
        window = NewStage.getStage("Prolong Checked-Out Book!", "/fxml/checkOut/books/prolongDialog.fxml");
        window.show();
    }

    @FXML
    private void datesBetween() throws Exception {
        window = NewStage.getStage("Checked-Out Between", "/fxml/checkOut/books/betweenDialog.fxml");
        window.show();
    }

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember() throws Exception {
        ChangeScene.init("/fxml/members/addMember.fxml", "Add Member");
    }

    //Find a Member
    @FXML
    private void findMemberByName() throws Exception {
        window = NewStage.getStage("Search by Name", "/fxml/members/search/byFullName.fxml");
        window.show();
    }

    @FXML
    private void findMemberByCity() throws Exception {
        window = NewStage.getStage("Search by City", "/fxml/members/search/byCityDialog.fxml");
        window.show();
    }

    @FXML
    private void findMemberByDayOfBirth() throws Exception {
        window = NewStage.getStage("Search by Day of Birth", "/fxml/members/search/byBDayDialog.fxml");
        window.show();
    }

    @FXML
    private void findMemberByPayed() throws Exception {
        MembersByNotPayed member = new MembersByNotPayed();
        member.search();
    }

    @FXML
    private void findMemberByAnalen() throws Exception {
        window = NewStage.getStage("Zoek op Analen niet ontvangen", "/fxml/members/search/byAnalenDialog.fxml");
        window.show();
    }

    //Update Member
    @FXML
    private void updateMember() throws Exception {
        window = NewStage.getStage("Update Member!", "/fxml/members/update/updateDialog.fxml");
        window.show();
    }

    //Delete Member
    @FXML
    private void deleteMember() {
    }

    //Mailings
    @FXML
    private void mailOverDue() {

    }

    @FXML
    private void mailBDay() {

    }

    @FXML
    private void mailTwoDaysNotice() {

    }

    @FXML
    private void mailBoard() {
        List<Member> memberList = repo.findByBoardIsTrue();
        String from = "d.demesmaecker@gmail.com";
        String host = "localhost";
        Member m = memberList.get(0);
        String to = m.getEmail();
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "8080");
        Session session = Session.getDefaultInstance(props);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Dit is een testmail");
            message.setText("Nico\nJe bent een slet!");
            Transport.send(message);
            Warning.alert("Messaging succes", "De mail werd succesvol verzonden");
        }catch(MessagingException mex){
            Warning.alert("Messaging Error", "Er ging iets fout");
            mex.printStackTrace();
        }
        /*Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "8080");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "8080");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("d.demesmaecker@gmail.com","SoetkinIsEen8erlijkeTrut");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            for(Member m : memberList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(m.getEmail()));
            }
                message.setSubject("Sletten");
                message.setText("Sletten en Tetten.\n Dit is een test message uit mijn javaFX programma.");
                Transport.send(message);
                Warning.alert("Message sent succesfully", "De boodschap werdt met succes verzonden.");
        }catch(MessagingException mex){
            Warning.alert("Messaging Error", "Er ging iets fout tijdens het versturen van de mail!");
            mex.printStackTrace();
        }*/
    }
}
