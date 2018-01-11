package be.kokw.controllers;

import be.kokw.bean.*;
import be.kokw.repositories.MemberRepo;
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

import static javafx.collections.FXCollections.observableArrayList;

/**
 * Created by ufotje on 20/10/2017.
 * This is the MenuControllerClass
 */

@Controller
public class MenuController {
    public static Stage window;
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
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    //BookMethods
    //Add a Book
    @FXML
    private void addBook() {
        try {
            ChangeScene.init("/fxml/books/create/addBook.fxml", "addBook");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Find a Book
    @FXML
    private void findBookByTitle() {
        try {
            window = NewStage.getStage("Vind Boek op Titel!", "/fxml/books/search/dialogpaneByTitle.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByAuthor() {
        try {
            window = NewStage.getStage("Vul de auteurs naam in!", "/fxml/books/search/byName.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByPublisher() {
        try {
            window = NewStage.getStage("Vind Boeken op uitgeverij!", "/fxml/books/search/dialogpaneByPublisher.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByTopic() {
        try {
            window = NewStage.getStage("Vind Boeken op onderwerp!", "/fxml/books/search/byTopicDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByISBN() {
        try {
            window = NewStage.getStage("Vind boeken op ISBN", "/fxml/books/search/byISBNDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByDepot() {
        try {
            window = NewStage.getStage("Vind boeken op depotnr", "/fxml/books/search/byDepotDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByBoughtOn() {
        try {
            window = NewStage.getStage("Vind boeken aangekocht op datum", "/fxml/books/search/byBoughtOnDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByBoughtOnAll() {
        try {
            ChangeScene.init("/fxml/books/found/bought/tableviewByBoughtOnAll.fxml", "Alle boeken die werden aangekocht");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void findByBoughtOnBetween() {
        try {
            window = NewStage.getStage("Vind boeken aangekocht tussen", "/fxml/books/search/byBoughtOnBetweenDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedOn() {
        try {
            window = NewStage.getStage("Vind boeken gedonneerd op datum", "/fxml/books/search/byGiftedOnDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedOnAll() {
        try {
            ChangeScene.init("/fxml/books/found/gifted/tableviewByGiftedOnAll.fxml", "Alle boeken die werden gedonneeerd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void findByGiftedOnBetween() {
        try {
            window = NewStage.getStage("Vind boeken gedonneerd tussen", "/fxml/books/search/byGiftedOnBetweenDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedForOnAll() {
        try {
            ChangeScene.init("/fxml/books/found/giftedFor/tableviewGiftedForOnAll.fxml", "Alle boeken die werden gedonneeerd tegenprestatie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void findByGiftedForOn() {
        try {
            window = NewStage.getStage("Vind Boeken tegenprestatie op datum", "/fxml/books/search/byGiftedForOnDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedForOnBetween() {
        try {
            window = NewStage.getStage("Vind boeken gedonneerd met tegenprestatie tussen", "/fxml/books/search/byGiftedForOnBetweenDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedForOnName() {
        try {
            window = NewStage.getStage("Vind boeken tegenprestatie op naam", "/fxml/books/search/byGiftedForOnName.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findByGiftedForOnNameAndDate() {
        try {
            window = NewStage.getStage("Vind boek tegenprestatie op naam en datum", "/fxml/books/search/byGiftedForOnNameAndDateDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //Update a book
    @FXML
    private void updateBookByTitle() {
        try {
            window = NewStage.getStage("Update een Boek!", "/fxml/books/update/updateDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //Delete a Book
    @FXML
    private void deleteBookByTitle() {
        window = NewStage.getStage("Verwijder Boek op Titel!", "/fxml/books/delete/dialogpaneByTitle.fxml");
        window.show();
    }

    @FXML
    private void derateBook() {
        try {
            window = NewStage.getStage("Declasseer een boek.", "/fxml/books/delete/derateDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //checkOut Methods
    @FXML
    private void checkOut() {
        try {
            window = NewStage.getStage("Check-Out Book!", "/fxml/checkOut/books/checkOutDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void returnBook() {
        try {
            window = NewStage.getStage("Return Book!", "/fxml/checkOut/books/returnDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void prolong() {
        try {
            window = NewStage.getStage("Prolong Checked-Out Book!", "/fxml/checkOut/books/prolongDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void datesBetween() {
        try {
            window = NewStage.getStage("Checked-Out Between", "/fxml/checkOut/books/betweenDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //MagazineMethods
    //Add a Magazine
    @FXML
    private void addMag() {
        try {
            ChangeScene.init("/fxml/magazines/create/addMagazine.fxml", "Add a Magazine");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete a Magazine
    @FXML
    private void deleteMag() {
    }

    //Search for Magazines
    @FXML
    private void magByIssn() {
    }

    @FXML
    private void magByName() {
        try {
            NewStage.getStage("Geef de titel van het magazine", "/fxml/magazines/search/dialogs/byTitleDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void magByPublisher() {
        try {
            NewStage.getStage("Geef de uitgever van het magazine", "/fxml/magazines/search/dialogs/byPublisherDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void magByTraded() {
        try {
            ChangeScene.init("/fxml/magazines/search/views/findMagByTradedView.fxml","Alle magzines met ruilabonnement.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void magBySubscription() {
        try {
            ChangeScene.init("/fxml/magazines/search/views/findMagBySubscribedView.fxml", "Find magazines by subscription");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void magByTopic() {
        try {
            ChangeScene.init("/fxml/magazines/search/dialogs/byTopicDialog.fxml", "Geef het onderwerp op");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //DigitalMethods
    //Add a Digital
    @FXML
    private void addDigi() {
    }

    //Delete a Digital
    @FXML
    private void deleteDigi() {
    }

    //Search for Digitals


    //Checkout Digi
    @FXML
    private void checkOutDVD() {
        try {
            window = NewStage.getStage("Check-Out DVD!", "/fxml/checkOut/dvd/checkOutDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void returnDVD() {
        try {
            window = NewStage.getStage("Return DVD!", "/fxml/checkOut/dvd/returnDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void prolongDVD() {
        try {
            window = NewStage.getStage("Prolong Checked-Out DVD!", "/fxml/checkOut/dvd/prolongDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void datesBetweenDVD() {
        try {
            window = NewStage.getStage("Checked-Out Between", "/fxml/checkOut/dvd/betweenDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember() {
        try {
            ChangeScene.init("/fxml/members/addMember.fxml", "Add Member");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Find a Member
    @FXML
    private void findMemberByName() {
        try {
            window = NewStage.getStage("Search by Name", "/fxml/members/search/byFullName.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findMemberByCity() {
        try {
            window = NewStage.getStage("Search by City", "/fxml/members/search/byCityDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findMemberByDayOfBirth() {
        try {
            window = NewStage.getStage("Search by Day of Birth", "/fxml/members/search/byBDayDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findMemberByPayed() {
        ObservableList<Member> memberList = observableArrayList(memberRepo.findByPayedIsFalse());
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden gevonden van wie het lidgeld niet betaal werd!");
        } else {
            String title = "Zoeken op Lidgeld niet betaald";
            initTable(title, memberList);
        }
    }

    @FXML
    private void findMemberByAnalen() {
        ObservableList<Member> memberList = observableArrayList(memberRepo.findByAnalIsFalse());
        if (memberList.isEmpty()) {
            Warning.alert("No Members found!", "Er werden geen leden gevonden die hun Analen nog niet ontvangen hebben!");
        } else {
            String title = "Zoeken op Analen niet ontvangen";
            initTable(title, memberList);

        }
    }

    private void initTable(String title, ObservableList<Member> memberList) {
        try {
            ChangeScene.init("/fxml/members/search/tableviewAnal.fxml", title);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void updateMember() {
        try {
            window = NewStage.getStage("Update Member!", "/fxml/members/update/updateDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    //Delete Member
    @FXML
    private void deleteMember() {
    }

    //Mailings
    @FXML
    private void mailMen() {
        try {
            window = NewStage.getStage("Mail naar alle manelijke leden", "/fxml/mailings/toBoard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();

    }

    @FXML
    private void mailWomen() {
        try {
            window = NewStage.getStage("Mail naar alle vrouwelijke leden", "/fxml/mailings/toBoard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();

    }

    @FXML
    private void mailMembers() {
        try {
            window = NewStage.getStage("Mail naar alle Leden", "/fxml/mailings/toBoard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void mailBoard() {
        try {
            window = NewStage.getStage("Mail naar Raad van Bestuur", "/fxml/mailings/toBoard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }
}
