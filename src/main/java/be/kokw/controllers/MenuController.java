package be.kokw.controllers;

import be.kokw.bean.*;
import be.kokw.repositories.CheckOutRepo;
import be.kokw.repositories.MemberRepo;
import be.kokw.repositories.TimeStampRepo;
import be.kokw.utility.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */

@Controller
public class MenuController {
    public static Stage window;
    private CheckOutRepo checkOutRepo;
    private TimeStampRepo timeStampRepo;
    private MemberRepo memberRepo;
    @FXML
    private TableView<Member> table;
    @FXML
    private TableColumn<Member, Integer> idCol, nrCol, zipCol;
    @FXML
    private TableColumn<Member, String> firstNameCol, lastNameCol, streetCol, cityCol, mailCol;
    @FXML
    private TableColumn<Member, LocalDate> bDayCol;
    @FXML
    private TableColumn<Member, Boolean> payedCol, analCol;


    @Autowired
    private void setCheckOutRepo(@Qualifier("checkOutRepo") CheckOutRepo checkOutRepo) {
        this.checkOutRepo = checkOutRepo;
    }

    @Autowired
    private void setTimeStampRepo(@Qualifier("timeStampRepo") TimeStampRepo timeStampRepo) {
        this.timeStampRepo = timeStampRepo;
    }

    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
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

    @FXML
    private void findBookByISBN() throws Exception {
        window = NewStage.getStage("Vind boeken op ISBN", "/fxml/books/search/byISBNDialog.fxml");
        window.show();
    }

    @FXML
    private void findBookByDepot() throws Exception {
        window = NewStage.getStage("Vind boeken op depotnr", "/fxml/books/search/byDepotDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedOn() throws Exception {
        window = NewStage.getStage("Vind boeken gedonneerd op datum", "/fxml/books/search/byGiftedOnDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOn() throws Exception {
        window = NewStage.getStage("Vind Boeken tegenprestatie op datum", "/fxml/books/search/byGiftedForOnDateDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnName() throws Exception {
        window = NewStage.getStage("Vind boeken tegenprestatie op naam", "/fxml/books/search/byGiftedForOnName.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnNameAndDate() throws Exception {
        window = NewStage.getStage("Vind boek tegenprestatie op naam en datum", "/fxml/books/search/byGiftedForOnNameAndDate.fxml");
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
        ObservableList<Member> memberList = observableArrayList(memberRepo.findByPayedIsFalse());
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden gevonden van wie het lidgeld niet betaal werd!");
        } else {
            String title = "Zoeken op Lidgeld niet betaald";
            initTable(title, memberList);
        }
    }

    @FXML
    private void findMemberByAnalen() throws Exception {
        ObservableList<Member> memberList = observableArrayList(memberRepo.findByAnalIsFalse());
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden gevonden die hun Analen nog niet ontvangen hebben!");
        } else {
            String title = "Zoeken op Analen niet ontvangen";
            initTable(title, memberList);

        }
    }

    private void initTable(String title, ObservableList<Member> memberList) throws Exception {
        ChangeScene.init("/fxml/members/search/tableviewAnal.fxml", title);
        table.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
        nrCol.setCellValueFactory(new PropertyValueFactory<>("houseNr"));
        zipCol.setCellValueFactory(new PropertyValueFactory<>("zip"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        bDayCol.setCellValueFactory(new PropertyValueFactory<>("bDay"));
        payedCol.setCellValueFactory(new PropertyValueFactory<>("payed"));
        analCol.setCellValueFactory(new PropertyValueFactory<>("anal"));
        table.setItems(memberList);
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
        List<CheckedOut> list = checkOutRepo.findByReturnDateBeforeAndReturnedIsFalse(LocalDate.now());
        for (CheckedOut c : list) {
            Member m = c.getMember();
            Book b = c.getBook();
            String name = m.getFirstName();
            String title = b.getTitle();
            Authors author = b.getAuthors();
            String text = "Geachte " + name + "\n \nHet boek: '" + title + "' geschreven door '" + author + "' werd te laat terug gebracht.\nGelieve zo spoedig mogelijk het boek in te leveren.\n \nMet vriendelijke groeten \n \n \nHet KOKW-Team";
            Mail.sendMail(m.getEmail(), "Boek Te Laat!", text);
        }

    }

    @FXML
    private void mailBDay() {
        long count = timeStampRepo.count();
        TimeStamp stamp = timeStampRepo.findOne(count);
        LocalDate latest = stamp.getLast();
        LocalDate now = LocalDate.now();
        List<Member> list = memberRepo.findByBDayBetween(latest, now);
        String topic = "Happy Birthday";
        String wens = "\n \nWij, van de KOKW, willen u laten weten dat ook bij ons uw verjaardag niet ongemerkt is gebleven en willen je van harte feliciteren op deze speciale dag.";
        String text;
        if (list.isEmpty()) {
            Warning.alert("No Members Found", "Er werden geen leden gevonden die jarig zijn!");
        } else {
            for (Member m : list) {
                LocalDate bDay = m.getbDay();
                if (bDay.equals(now)) {
                    text = "Beste " + m.getFirstName() + wens + "\n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                    Mail.sendMail(m.getEmail(), topic, text);
                } else {
                    Date d1 = Date.from(bDay.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    Date d2 = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    long difference = d2.getTime() - d1.getTime();
                    text = "Beste " + m.getFirstName() + wens + "\nOok al zijn we " + difference + " dag(en) te hopen wij dat je toch genoten hebt van je verjaardag en wensen we je nog vele jaren. \n \nMet Vriendelijke Groeten\n \n \nHet KOKW-Team";
                    Mail.sendMail(m.getEmail(), topic, text);
                }
            }
        }

    }

    @FXML
    private void mailTwoDaysNotice() {
        LocalDate returnDate = LocalDate.now().plusDays(2);
        List<CheckedOut> list = checkOutRepo.findByReturnDateAndReturnedIsFalse(returnDate);
        if (list.isEmpty()) {
            Warning.alert("No Items Found", "Er werden geen boeken gevonden die binnen de 2 dagen dienen worden terug gebracht!");
        } else {
            for (CheckedOut c : list) {
                String name = c.getFullName();
                String title = c.getTitle();
                Member m = c.getMember();
                String topic = "Uw uitleenbeurt vervalt binnen 2 dagen...";
                String text = "Geachte " + name + "\n \nUw uitleenbeurt voor het boek '" + title + "' vervalt binnen 2 dagen!\nVergeet niet tijdig het boek binnen te brengen.\n \nMet vriendelijke groeten\n \n \n \nHet KOKW-Team";
                Mail.sendMail(m.getEmail(), topic, text);
            }
        }
    }

    @FXML
    private void mailBoard() throws Exception {
        window = NewStage.getStage("Mail naar Raad van Bestuur", "/fxml/maillings/toBoard.fxml");
        window.show();
    }
}
