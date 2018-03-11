package be.kokw.controllers;

import be.kokw.bean.*;
import be.kokw.repositories.MemberRepo;
import be.kokw.utility.controller.tables.MemberTable;
import be.kokw.utility.sceneControl.ChangeScene;
import be.kokw.utility.sceneControl.NewStage;
import be.kokw.utility.validation.Warning;
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


    @Autowired
    private void setMemberRepo(@Qualifier("memberRepo") MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @FXML
    private void home() {
        ChangeScene.init("/fxml/home.fxml", "KOKW - Het Verleden Draait Altijd Door!");
    }

    //BookMethods
    //Add a Book
    @FXML
    private void addBook() {
        ChangeScene.init("/fxml/books/create/addBook.fxml", "addBook");
    }

    //Find a Book
    @FXML
    private void findBookByTitle() {
        window = NewStage.getStage("Vind Boek op Titel!", "/fxml/books/search/other/dialogpaneByTitle.fxml");

        window.show();
    }

    @FXML
    private void findBookByAuthor() {
        window = NewStage.getStage("Vul de auteurs naam in!", "/fxml/books/search/other/byName.fxml");
        window.show();
    }

    @FXML
    private void findBookByPublisher() {
        window = NewStage.getStage("Vind Boeken op uitgeverij!", "/fxml/books/search/other/dialogpaneByPublisher.fxml");
        window.show();
    }

    @FXML
    private void findBookByTopic() {
        window = NewStage.getStage("Vind Boeken op onderwerp!", "/fxml/books/search/other/byTopicDialog.fxml");
        window.show();
    }

    @FXML
    private void findBookByISBN() {
        try {
            window = NewStage.getStage("Vind boeken op ISBN", "/fxml/books/search/other/byISBNDialog.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        window.show();
    }

    @FXML
    private void findBookByDepot() {
        window = NewStage.getStage("Vind boeken op depotnr", "/fxml/books/search/other/byDepotDialog.fxml");
        window.show();
    }

    @FXML
    private void findByBoughtOn() {
        window = NewStage.getStage("Vind boeken aangekocht op datum", "/fxml/books/search/bought/byBoughtOnDialog.fxml");
        window.show();
    }

    @FXML
    private void findByBoughtOnAll() {
        ChangeScene.init("/fxml/books/found/bought/tableviewByBoughtOnAll.fxml", "Alle boeken die werden aangekocht");
    }

    @FXML
    private void findByBoughtOnBetween() {
        window = NewStage.getStage("Vind boeken aangekocht tussen", "/fxml/books/search/bought/byBoughtOnBetweenDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedOn() {
        window = NewStage.getStage("Vind boeken gedonneerd op datum", "/fxml/books/search/donated/byGiftedOnDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedOnAll() {
        ChangeScene.init("/fxml/books/found/gifted/tableviewByGiftedOnAll.fxml", "Alle boeken die werden gedonneeerd");
    }

    @FXML
    private void findByGiftedOnBetween() {
        window = NewStage.getStage("Vind boeken gedonneerd tussen", "/fxml/books/search/donated/byGiftedOnBetweenDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnAll() {
        ChangeScene.init("/fxml/books/found/giftedFor/tableviewGiftedForOnAll.fxml", "Alle boeken die werden gedonneeerd tegenprestatie");
    }

    @FXML
    private void findByGiftedForOn() {
        window = NewStage.getStage("Vind Boeken tegenprestatie op datum", "/fxml/books/search/traded/byGiftedForOnDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnBetween() {
        window = NewStage.getStage("Vind boeken gedonneerd met tegenprestatie tussen", "/fxml/books/search/traded/byGiftedForOnBetweenDialog.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnName() {
        window = NewStage.getStage("Vind boeken tegenprestatie op naam", "/fxml/books/search/traded/byGiftedForOnName.fxml");
        window.show();
    }

    @FXML
    private void findByGiftedForOnNameAndDate() {
        window = NewStage.getStage("Vind boek tegenprestatie op naam en datum", "/fxml/books/search/traded/byGiftedForOnNameAndDateDialog.fxml");
        window.show();
    }

    //Update a book
    @FXML
    private void updateBookByTitle() {
        window = NewStage.getStage("Update een Boek!", "/fxml/books/update/updateDialog.fxml");
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
        window = NewStage.getStage("Declasseer een boek.", "/fxml/books/delete/derateDialog.fxml");
        window.show();
    }

    //checkOut Methods
    @FXML
    private void checkOut() {
        window = NewStage.getStage("Check-Out Book!", "/fxml/checkOut/books/checkOutDialog.fxml");
        window.show();
    }

    @FXML
    private void returnBook() {
        window = NewStage.getStage("Return Book!", "/fxml/checkOut/books/returnDialog.fxml");
        window.show();
    }

    @FXML
    private void prolong() {
        window = NewStage.getStage("Prolong Checked-Out Book!", "/fxml/checkOut/books/prolongDialog.fxml");
        window.show();
    }

    @FXML
    private void datesBetween() {
        window = NewStage.getStage("Checked-Out Between", "/fxml/checkOut/books/betweenDialog.fxml");
        window.show();
    }

    //MagazineMethods
    //Add a Magazine
    @FXML
    private void addMag() {
        ChangeScene.init("/fxml/magazines/create/addMagazine.fxml", "Add a Magazine");
    }

    //Delete a Magazine
    @FXML
    private void deleteMag() {
    }

    //Search for Magazines
    @FXML
    private void magByIssn() {
        window = NewStage.getStage("Geef het issn van het magazine","/fxml/magazines/search/dialogs/byIssnDialog.fxml");
        window.show();
    }

    @FXML
    private void magByName() {
        window = NewStage.getStage("Geef de titel van het magazine", "/fxml/magazines/search/dialogs/byTitleDialog.fxml");
        window.show();
    }

    @FXML
    private void magByPublisher() {
        window = NewStage.getStage("Geef de uitgever van het magazine", "/fxml/magazines/search/dialogs/byPublisherDialog.fxml");
        window.show();
    }

    @FXML
    private void magByTraded() {
        ChangeScene.init("/fxml/magazines/search/views/findMagByTradedView.fxml", "Alle magzines met ruilabonnement.");
    }

    @FXML
    private void magBySubscription() {
        ChangeScene.init("/fxml/magazines/search/views/findMagBySubscribedView.fxml", "Find magazines by subscription");
    }

    @FXML
    private void magBySubscriptionFulfilled() {
        ChangeScene.init("/fxml/magazines/search/views/findMagBySubscriptionFulfilledView.fxml", "Find magazines by subscription fulfilled.");
    }

    @FXML
    private void magBySubscriptionNotFulfilled() {
        ChangeScene.init("/fxml/magazines/search/views/findMagBySubscriptionNotFulfilledView.fxml", "Find magazines by subscription not fulfilled.");
    }

    @FXML
    private void magByTopic() {
        window = NewStage.getStage("Geef het onderwerp op", "/fxml/magazines/search/dialogs/byTopicDialog.fxml");
        window.show();
    }

    //DigitalMethods
    //Add a Digital
    @FXML
    private void addDigi() {
        ChangeScene.init("/fxml/digital/create/addDigital.fxml", "Voeg een nieuwe digitale drager toe");
    }

    //Delete a Digital
    @FXML
    private void deleteDigi() {
    }

    //Search for Digitals
    @FXML
    private void digiOnTitle() {
        window = NewStage.getStage("Vind digitale dragers op title", "/fxml/digital/search/other/dialogpaneByTitle.fxml");
        window.show();
    }

    @FXML
    private void digiOnPublisher() {
        window = NewStage.getStage("Vind digitale dragers op uitgever", "/fxml/digital/search/other/byTopicDialog.fxml");
        window.show();
    }

    @FXML
    private void digiOnTopic() {
        window = NewStage.getStage("Vind digitale dragers op onderwerp", "/fxml/digital/search/other/byTopicDialog.fxml");
        window.show();
    }

    @FXML
    private void digiOnAuthor() {
        window = NewStage.getStage("Vind digitale dragers op regisseur", "/fxml/digital/search/other/byName.fxml");
        window.show();
    }

    @FXML
    private void digiOnDepot() {
        window = NewStage.getStage("Vind digitale dragers op depotnummer", "/fxml/digital/search/other/byDepotDialog.fxml");
        window.show();
    }

    @FXML
    private void digiBoughtAll() {
        ChangeScene.init("/fxml/digital/found/bought/tableviewByBoughtOnAll.fxml", "Alle aangekochte digitale dragers");
    }

    @FXML
    private void digiBoughtOn() {
        window = NewStage.getStage("Alle digitale dragers gekocht op", "/fxml/digital/search/bought/byBoughtOnDialog.fxml");
        window.show();
    }

    @FXML
    private void digiBoughtBetween() {
        window = NewStage.getStage("Alle digitale dragers aangekocht tussen", "/fxml/digital/found/boughtfxml/digital/search/bought/byBoughtOnBetweenDialog.fxmll");
        window.show();
    }

    @FXML
    private void digiDonatedAll() {
        ChangeScene.init("/fxml/digital/found/donated/tableviewByGiftedOnAll.fxml", "Alle gedoneerde digitale dragers");
    }

    @FXML
    private void digiDonatedOn() {
        window = NewStage.getStage("Alle gedoneerde digitale dragers op", "/fxml/digital/search/donated/byGiftedOnDialog.fxml");
        window.show();

    }

    @FXML
    private void digiDonatedBetween() {
        window = NewStage.getStage("Alle gedoneerde digitale dragers gedoneerd tussen", "/fxml/digital/search/donated/byGiftedOnBetweenDialog.fxml");
        window.show();
    }

    @FXML
    private void digiDonatedBy() {
        window = NewStage.getStage("Alle gedoneerde digitale dragers gedoneerd door", "/fxml/digital/search/donated/donatedBy.fxml");
        window.show();
    }

    @FXML
    private void digiTradedAll() {
        ChangeScene.init( "/fxml/books/found/giftedFor/tableviewGiftedForOnAll.fxml","Alle geruilde digitale dragers");
    }

    @FXML
    private void digiTradedOn() {
        window = NewStage.getStage("TradeDate", "/fxml/digital/search/traded/byGiftedForOnDialog.fxml");
        window.show();
    }

    @FXML
    private void digiTradedBetween() {
        window = NewStage.getStage("Geruilde digitale dragers tussen", "/fxml/digital/search/traded/byGiftedForOnBetweenDialog.fxml");
        window.show();
    }

    @FXML
    private void digiTradedWith() {
        window = NewStage.getStage("Digitale dragers geruild met", "/fxml/digital/search/traded/byGiftedForOnName.fxml");
        window.show();
    }


    //Checkout Digi
    @FXML
    private void checkOutDVD() {
        window = NewStage.getStage("Check-Out DVD!", "/fxml/checkOut/dvd/checkOutDialog.fxml");
        window.show();
    }

    @FXML
    private void returnDVD() {
        window = NewStage.getStage("Return DVD!", "/fxml/checkOut/dvd/returnDialog.fxml");
        window.show();
    }

    @FXML
    private void prolongDVD() {
        window = NewStage.getStage("Prolong Checked-Out DVD!", "/fxml/checkOut/dvd/prolongDialog.fxml");
        window.show();
    }

    @FXML
    private void datesBetweenDVD() {
        window = NewStage.getStage("Checked-Out Between", "/fxml/checkOut/dvd/betweenDialog.fxml");
        window.show();
    }

    //MemberMethods
    //Add a Member
    @FXML
    private void addMember() {
        ChangeScene.init("/fxml/members/addMember.fxml", "Add Member");
    }

    //Find a Member
    @FXML
    private void findMemberByName() {
        window = NewStage.getStage("Search by Name", "/fxml/members/search/byFullName.fxml");
        window.show();
    }

    @FXML
    private void findMemberByCity() {
        window = NewStage.getStage("Search by City", "/fxml/members/search/byCityDialog.fxml");
        window.show();
    }

    @FXML
    private void findMemberByDayOfBirth() {
        window = NewStage.getStage("Search by Day of Birth", "/fxml/members/search/byBDayDialog.fxml");
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
        ChangeScene.init("/fxml/members/search/tableviewAnal.fxml", "Leden die geen annalen ontvangen hebben.");

    }

    private void initTable(String title, ObservableList<Member> memberList) {

    }

    //Update Member
    @FXML
    private void updateMember() {
        window = NewStage.getStage("Update Member!", "/fxml/members/update/updateDialog.fxml");
        window.show();
    }

    //Delete Member
    @FXML
    private void deleteMember() {
    }

    //Mailings
    @FXML
    private void mailMen() {
        window = NewStage.getStage("Mail naar alle mannelijke leden", "/fxml/mailings/toBoard.fxml");
        window.show();
    }

    @FXML
    private void mailWomen() {
        window = NewStage.getStage("Mail naar alle vrouwelijke leden", "/fxml/mailings/toBoard.fxml");
        window.show();
    }

    @FXML
    private void mailMembers() {
        window = NewStage.getStage("Mail naar alle Leden", "/fxml/mailings/toBoard.fxml");
        window.show();
    }

    @FXML
    private void mailBoard() {
        window = NewStage.getStage("Mail naar Raad van Bestuur", "/fxml/mailings/toBoard.fxml");
        window.show();
    }
}
